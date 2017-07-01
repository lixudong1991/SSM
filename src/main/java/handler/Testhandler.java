package handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lxd.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import service.Studentservice;

@Controller
public class Testhandler {
    @Resource
    private Studentservice students;

    @RequestMapping("/students")
    public String list(Map<String, Object> map) {
        try {
            map.put("students", students.findas());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("student", new Student());
        return "input";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Student st) {
        try {

            String name=st.getName();
            if(name!=null&&name.length()>0){
                StringBuffer sb=new StringBuffer();
                String str=st.getCall();
                if(str.length()==11) {
                    for (int i = 0; i < 11; i++) {
                        if (!Character.isDigit(str.charAt(i))) {
                            break;
                        }
                        sb.append(str.charAt(i));
                    }
                }
                if(sb.toString().length()==11){
                    st.setCall(sb.toString());
                    students.inserts(st);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:students";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        try {
            students.deletes(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:students";
    }

    @RequestMapping("look")
    public String look(@RequestParam("path") String path, @RequestParam("name") String name) {
        String s = "";
        try {
            s = URLEncoder.encode(path + "/" + name, "UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return "redirect:play?path=" + s;
    }

    @RequestMapping("play")
    public String testResponseEntity(@RequestParam("path") String path, HttpServletResponse response, Map<String, Object> map) {
        if (path == null || path.length() == 0) {
            return "redirect:index.jsp";
        }
        File f = new File(path);
        if (f.isDirectory()) {
            map.put("pat", f.getPath().replace('\\', '/'));
            map.put("ppath", "");
            if (f.getParent() != null) {
                map.put("ppath", f.getParent().replace('\\', '/'));
            }
            map.put("items", f.list());

            return "videos";
        }
        response.setCharacterEncoding("utf-8");
        //response.setContentType(m.get(name.substring(name.lastIndexOf("."))));
        //  System.out.println(m.get(name.substring(name.lastIndexOf("."))));
        response.setHeader("Content-Length", f.length() + "");

        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
            OutputStream os;
            InputStream in;
            byte[] body = new byte[4096];
            in = new FileInputStream(f);
            os = response.getOutputStream();
            int length;
            while ((length = in.read(body)) > 0) {
                os.write(body, 0, length);
            }
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("FileUpload")
    public String FileUpload(@RequestParam("path") String path, HttpServletRequest request, Map<String, Object> map) throws IllegalStateException, IOException {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    if(!path.endsWith("/"))
                        path+="/";
                    String pat =file.getOriginalFilename();
                    System.out.println(pat);
                    if(pat==null||pat.length()==0){
                        continue;
                    }
                    //上传
                    file.transferTo(new File(path+pat));
                }

            }

        }
        File f = new File(path);
        map.put("pat", f.getPath().replace('\\', '/'));
        map.put("ppath", "");
        if (f.getParent() != null) {
            map.put("ppath", f.getParent().replace('\\', '/'));
        }
        map.put("items", f.list());
        return "videos";
    }
    @RequestMapping("deletefile")
    public String deletefile(@RequestParam("path")String path,@RequestParam(value="delete",required = false) String[] name,Map<String, Object> map){
        if(name!=null&&name.length!=0) {
            if (!path.endsWith("/"))
                path += "/";
            System.out.println(path);
            for (String e : name) {
                fun(path + e);
            }
        }
        File f = new File(path);
        map.put("pat", f.getPath().replace('\\', '/'));
        map.put("ppath", "");
        if (f.getParent() != null) {
            map.put("ppath", f.getParent().replace('\\', '/'));
        }
        map.put("items", f.list());
        return "videos";
    }
    private void fun(String file){
        File f=new File(file);
        if(f.isFile()){
            f.delete();
            return;
        }
        File[] fs=f.listFiles();
        for(File fi:fs){
            fun(fi.getPath());
        }
        f.delete();
    }
}
