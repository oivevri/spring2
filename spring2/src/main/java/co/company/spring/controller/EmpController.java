package co.company.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.company.spring.dao.Department;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpSearch;
import co.company.spring.dao.Job;
import co.company.spring.emp.service.EmpService;

@Controller
public class EmpController {
   @Autowired
   EmpService service;
   
   @RequestMapping("/ajax/jobSelect")
   @ResponseBody // 이거 ResponseBody까지 합쳐서 Rest컨트롤러랑 같은거
   public List<Job> jobSelect() {
	   return service.jobSelect();
   }
   
   // 다른 메소드에서 굳이 쓸 필요없이 모델어트리뷰트로 써서, 인수나 메소드안에 쓰이게 한다
   // ModelAttribute("여기") 값이 jsp에서 items="${이거}"랑 같아야한다
   @ModelAttribute("jobs")
   public List<Job> jobs(){
	   return service.jobSelect();
   }
   @ModelAttribute("departments")
   public List<Department> depts(){
	   return service.departmentSelect();
   }
   
   @RequestMapping(value="/empSelect", method=RequestMethod.GET)
   public ModelAndView select(EmpSearch emp) {
	   ModelAndView mav= new ModelAndView();
      //조회
	   mav.addObject("list",service.getEmpList(emp));
	   mav.setViewName("emp/select");
      return mav;
   }
   
   @GetMapping("/empInsertForm")
   public String insertForm(Emp emp,Model model) {
      return "emp/insert";
   }
   
   @GetMapping("/empUpdateForm")
   public String updateForm(Emp emp,Model model) {
	   model.addAttribute("emp",service.getEmp(emp));
      return "emp/insert";
   }
    
   @PostMapping("/empInsert")
   public String insert(Model model, Emp emp, Errors errors) {
	   // 항상 커맨드 객체 뒤에 에러가 와야 한다
	   // 객체 생성하고, validate 메서드에 검사할 커맨드객체emp와, 검사결과를 담을 error 객체를 담아주면
	   new EmpValidator().validate(emp, errors);
	   if (errors.hasErrors()) { // 에러가 하나라도 있다면
		   return "emp/insert"; // 페이지로 돌아감
	   }
	   
	   if(emp.getEmployeeId()==null)
		   service.insertEmp(emp);
	   else
		   service.updateEmp(emp);
	return "emp/insertOutput";
	   
   }
   
//   @PostMapping("/empInsert")
//   public String insert(@RequestParam (required=false,defaultValue = "noname" )String lastName,
//		   @RequestParam (required=false,defaultValue = "nomoney" )int salary,Emp emp) {
//	  //파라미터
//	  System.out.println(lastName);
//	  System.out.println(salary);
//	  System.out.println(emp);
//      //등록처리
//      //redirect(url값을 넣어줘야한다)
//      return "redirect:empSelect";
//      
//   }

}