package com.tianjian.rest;

import com.tianjian.data.bean.RealtionFile;
import com.tianjian.data.service.RealtionFileDao;
import com.tianjian.model.view.ResponseData;
import com.tianjian.storage.StorageFileNotFoundException;
import com.tianjian.storage.StorageService;
import com.tianjian.util.MsgDigestDemo;
import com.tianjian.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/images")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    RealtionFileDao relationFileDao;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/show")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/getFilesByRelation")
    @ResponseBody
    public ResponseData<List<RealtionFile>> getRealtionFiles(@RequestParam("relation_id") String relation_id) {
        ResponseData<RealtionFile> responseData = new ResponseData<>();
        List<RealtionFile> realtionFiles = relationFileDao.findByRealtionId(relation_id);
        return new ResponseData<List<RealtionFile>>("success", realtionFiles, 000);

    }

    @GetMapping("/deleteRelationData")
    @ResponseBody
    public ResponseData<Boolean> deleteRelationData(@RequestParam("relationFileId") String relationId) {
        relationFileDao.deleteById(relationId);
        return new ResponseData<Boolean>("success", true, 000);

    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseData<Boolean> handleFileUpload(
            @RequestParam("file") MultipartFile[] file,
            @RequestParam("relation_id") String relation_id,
            RedirectAttributes redirectAttributes
    ) throws NoSuchAlgorithmException {
        for(MultipartFile detail : file) {

            String filename = StringUtils.cleanPath(detail.getOriginalFilename());
            String digestName = MsgDigestDemo.getMsgDigestByMD5(relation_id + ":" + filename + new Date());
            //存储图片数据
            storageService.store(detail,digestName);
            RealtionFile realtionFile = new RealtionFile();
            realtionFile.setRelationFileId(UUIDUtil.getPreUUID("STATIC"));
            realtionFile.setResourceCode(digestName);
            realtionFile.setRealtionId(relation_id);
            relationFileDao.save(realtionFile);
        }
        return new ResponseData<Boolean>("upload success", true, 000);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Object getDataSource() {
        List<Object> data= new ArrayList<Object>();
        data.add(relationFileDao.findAll());
        //data.add(staticFileResourceDao.findAll());
        return data;
    }

}
