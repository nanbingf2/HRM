package com.rogercw.web.controller;

import com.rogercw.po.Document;
import com.rogercw.po.User;
import com.rogercw.service.DocumentService;
import com.rogercw.util.constant.HrmConstants;
import com.rogercw.util.tag.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2018/5/25.
 */
@Controller()
@RequestMapping("/document")
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @RequestMapping("/findDocumentByParam")
    public ModelAndView findDocumentByParams(Document document, Integer pageIndex){
        ModelAndView mv=new ModelAndView();
        PageModel pageModel=new PageModel();
        if (pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Document> documentList=documentService.findDocumentByParam(document,pageModel);
        mv.addObject("documentList",documentList);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("document/document");
        return mv;
    }

    @RequestMapping("/deleteDocument")
    public String deleteDocument(String ids){
        String[] idArray=ids.split(",");
        for (String id:idArray){
            documentService.deleteDocument(Integer.parseInt(id));
        }
        return "redirect:/document/findDocumentByParam";
    }

    @RequestMapping("showAddDocument")
    public String showAddDocument(){
        return "document/showAddDocument";
    }

    @RequestMapping(value = "/insertDocument",method = RequestMethod.POST)
    public String insertDocument(Document document, HttpSession session,
                                 @RequestParam("file")MultipartFile file) throws IOException {
        //获取文件名
        String filename=file.getOriginalFilename();
        //设置日期
        Date date=new Date();
        document.setCreateDate(date);
        String path=session.getServletContext().getRealPath("/upload");
        File realFile=new File(path,filename);
        if (!realFile.getParentFile().exists()){
            realFile.getParentFile().mkdirs();
        }
        //将上传的文件保存到服务器中
        file.transferTo(realFile);

        document.setFilename(filename);
        document.setPath(path+File.separator+filename);
        //从session中获取当前用户
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        document.setUser(user);
        documentService.insertDocument(document);
        return "redirect:/document/findDocumentByParam";
    }

    /**
     * 更新页面
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateDocument")
    public String showUpdateDocument(@RequestParam("id") int id, Model model){
        //查询数据以便更新时回显数据
        Document document=documentService.findDocumentById(id);
        model.addAttribute(document);
        return "document/showUpdateDocument";
    }

    @RequestMapping("/updateDocument")
    public String updateDocument(Document document){
        documentService.updateDocument(document);
        return "redirect:/document/findDocumentByParam";
    }


    @RequestMapping("/download")
    public ResponseEntity download(Integer id) throws Exception {
        Document document=documentService.findDocumentById(id);
        String filename=document.getFilename();
        //根据文件路径获取文件
        File file=new File(document.getPath());
        HttpHeaders headers=new HttpHeaders();
        //解决文件名的中文乱码问题
        filename=new String(filename.getBytes("UTF-8"),"iso-8859-1");

        headers.setContentDispositionFormData("attachment",filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}

