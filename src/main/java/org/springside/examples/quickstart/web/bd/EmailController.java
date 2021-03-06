package org.springside.examples.quickstart.web.bd;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.Email;
import org.springside.examples.quickstart.entity.Group;
import org.springside.examples.quickstart.entity.Level;
import org.springside.examples.quickstart.service.bd.EmailService;
import org.springside.examples.quickstart.service.bd.GroupService;
import org.springside.examples.quickstart.service.bd.KeyWordsService;
import org.springside.examples.quickstart.service.bd.LevelService;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;

/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /task/ Create page : GET /task/create Create action : POST
 * /task/create Update page : GET /task/update/{id} Update action : POST
 * /task/update Delete action : GET /task/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/bd/email")
public class EmailController {

	private static final String PAGE_SIZE = "50";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("code", "编码");
		sortTypes.put("name", "姓名");
	}

	private EmailService emailService;
	private GroupService groupService;
	private LevelService levelService;
	private KeyWordsService keywordsService;

	public KeyWordsService getKeywordsService() {
		return keywordsService;
	}

	@Autowired
	public void setKeywordsService(KeyWordsService keywordsService) {
		this.keywordsService = keywordsService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public LevelService getLevelService() {
		return levelService;
	}

	@Autowired
	public void setLevelService(LevelService levelService) {
		this.levelService = levelService;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@RequestMapping(value = "queryAll", method = RequestMethod.GET)
	@ResponseBody
	public Object queryEmails(Model model) {
		List<Email> emails = emailService.getAllEmail();
		return emails;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		// Long userId = getCurrentUserId();

		Page<Email> emails = emailService.getUserEmail(searchParams,
				pageNumber, pageSize, sortType);

		model.addAttribute("emails", emails);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "bd/email/emailList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("email", new Email());
		model.addAttribute("action", "create");

		List<Group> groups = groupService.getAllGroup();
		model.addAttribute("groupList", groups);
		List<Level> levels = levelService.getAllLevel();
		model.addAttribute("levelList", levels);

		return "bd/email/emailForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Email newEmail, Errors errors,
			RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "必填信息不能为空");
			return "redirect:/bd/email/";
		}
		emailService.saveEmail(newEmail);

		redirectAttributes.addFlashAttribute("message", "创建邮箱成功");
		return "redirect:/bd/email/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("email", emailService.getEmail(id));
		model.addAttribute("action", "update");
		List<Group> groups = groupService.getAllGroup();
		model.addAttribute("groupList", groups);
		List<Level> levels = levelService.getAllLevel();
		model.addAttribute("levelList", levels);

		return "bd/email/emailForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("email") Email email,
			RedirectAttributes redirectAttributes) {
		emailService.saveEmail(email);

		redirectAttributes.addFlashAttribute("message", "更新邮箱成功");
		return "redirect:/bd/email/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		emailService.deleteEmail(id);
		redirectAttributes.addFlashAttribute("message", "删除邮箱成功");
		return "redirect:/bd/email/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getTask(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
	}

}
