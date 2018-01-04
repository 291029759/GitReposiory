package ule.com.etl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ule.com.etl.model.User;
import ule.com.etl.service.UserService;

@Controller
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "etl/login")
	public String login123(User user,HttpSession session) {
		return "forward:login.jsp";
	}
	@RequestMapping(value = "etl/entry")
	public String login(User user,HttpSession session) {
		String userpwd=user.getPwd();
		String pwd=userService.getPwd(user);
		if(pwd!=null&&userpwd!=null){
			if(pwd.equals(userpwd)){
				session.setAttribute("user", user);
				return "forward:index.jsp";
			}
		}
		return "redirect:login.do";
	}
    //查询所有用户信息
    @RequestMapping(value = "etl/getAllUser")
    @ResponseBody
	public String getAllUser(HttpServletRequest request){
        List<User> allUser = userService.getAllUser();
        JSONObject obj=new JSONObject();
        obj.put("Rows",allUser);
        obj.put("Total",allUser.size());
        String bString=obj.toJSONString();
        return bString;
    }
    //插入用户信息
    @RequestMapping(value = "etl/insertUser")
    @ResponseBody
    public String insertUser(HttpServletRequest request){
        int maxId = userService.getMaxId();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        if(user != null){
            return "false" ;
        }else {
            String pwd = request.getParameter("pwd");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String flag = request.getParameter("flag");
            userService.insertUser(maxId+1,username,pwd,email,phone,Integer.valueOf(flag));
            return "The new user account has been added  successfully now  ...";
        }
    }
    //更新用户信息
    @RequestMapping(value = "etl/updateUser")
    @ResponseBody
    public ModelAndView updateUser(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        mv.addObject("user",user) ;
        mv.setViewName("updateUser");
        return mv ;
    }
    //删除用户信息
    @RequestMapping(value = "etl/deleteUser")
    @ResponseBody
    public String deleteUser(HttpServletRequest request){
        String id = request.getParameter("id");
        userService.deleteUser(Integer.valueOf(id));
        return "redirect:etlUser.jsp";
    }
    //更新用户信息
    @RequestMapping(value = "etl/userSubmit")
    @ResponseBody
    public String userSubmit(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String flag = request.getParameter("flag");
        userService.updateUser(Integer.valueOf(id),username,pwd,email,phone,Integer.valueOf(flag));
        return "THE ACCOUNT INFO HAS BEAN MODIFYED IN SUCESS  ..." ;
    }
}
