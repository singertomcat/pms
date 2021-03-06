package org.springside.examples.quickstart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 按日期判断，同时按网址判断，如果这个网址已经抓取过了将不再抓取(为解决没发布日期的情况)
 * 
 * @author wues
 * 
 */
@Entity
@Table(name = "pms_url")
public class Url implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6612407087175243007L;
	
	protected Long id;
	private String code;
	private String name;

	/**
	 * 省份，中央直属的写“中央”
	 */
	private String province;
	/**
	 * 部委：科技部、农业部等
	 */
	private String department;
	/**
	 * 板块
	 */
	private String module;
	/**
	 * 子版块
	 */
	private String submodule;

	private String url;
	/**
	 * 注意，url_prefix不能加html页面；href连接看第一个是/，或者是http，或者是.，或者是..之类的要特别注意
	 */
	private String urlPrefix;

	/**
	 * 主题内容xpath,表格中写的相对路径或绝对路径 抓取后更新此字段
	 */
	private String subjPath;
	/**
	 * 截取出主题后需要替换的字符串 key=value;key=value用这样的键值对存储
	 */
	private String subjReplace;

	/**
	 * 抓取连接的xpath
	 */
	private String linkPath;

	/**
	 * 抓取类型 NORMAL===>普通html SCRIPT===>抓取前需要先执行script脚本，然后再解析
	 */
	private String catchType = "NORMAL";

	/**
	 * 发布日期对应的xpath路径
	 */
	private String datePath;
	/**
	 * 截取出发布日期后需要替换掉的字符串 key=value;key=value用这样的键值对存储
	 */
	private String dateReplace;

	private String description;
	/**
	 * 当前网站的编码
	 */
	private String charset;

	/**
	 * 从哪个序列号开始读取，默认从1开始
	 */
	private Integer startBegin;

	/**
	 * 1:继续抓取下一页 0:不抓取下一页
	 */
	private String catchNextPage;
	/**
	 * 下一页的xpath
	 */
	private String nextPageXpath;

	/**
	 * 中部：MIDDLE；东部：EAST：西部：WEST；
	 */
	private String area;

	/**
	 * 是否为省会：1：是；0：否
	 */
	private String isCapital;

	/**
	 * 是否有效：1：是；0：否
	 */
	private String valid;

	/**
	 * 最近一次抓取成功失败标志：1：成功；0：失败
	 */
	private String success;

	/**
	 * 最近一次抓取时间
	 */
	private String catchTime;

	/**
	 * 最后一次错误消息
	 */
	private String errMsg;

	/**
	 * 是否按关键词筛选：0：否；1：是
	 */
	private String filter;

	private Rule rule;

	private CatchTask task;

	private Level level;
	private Group group;

	/**
	 * 上次抓取条数
	 */
	private String catchNum;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "catch_num")
	public String getCatchNum() {
		return catchNum;
	}

	public void setCatchNum(String catchNum) {
		this.catchNum = catchNum;
	}

	@Column(name = "err_msg")
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Column(name = "success")
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Column(name = "catch_time")
	public String getCatchTime() {
		return catchTime;
	}

	public void setCatchTime(String catchTime) {
		this.catchTime = catchTime;
	}

	@Column(name = "filter")
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@ManyToOne
	@JoinColumn(name = "level_id")
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Column(name = "catch_type")
	public String getCatchType() {
		return catchType;
	}

	public void setCatchType(String catchType) {
		this.catchType = catchType;
	}

	@ManyToOne
	@JoinColumn(name = "group_id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "valid")
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "is_capital")
	public String getIsCapital() {
		return isCapital;
	}

	public void setIsCapital(String isCapital) {
		this.isCapital = isCapital;
	}

	@Column(name = "catch_next_page")
	public String getCatchNextPage() {
		return catchNextPage;
	}

	public void setCatchNextPage(String catchNextPage) {
		this.catchNextPage = catchNextPage;
	}

	@Column(name = "next_page_xpath")
	public String getNextPageXpath() {
		return nextPageXpath;
	}

	public void setNextPageXpath(String nextPageXpath) {
		this.nextPageXpath = nextPageXpath;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "charset")
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Column(name = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "start_begin")
	public Integer getStartBegin() {
		return startBegin;
	}

	public void setStartBegin(Integer startBegin) {
		this.startBegin = startBegin;
	}

	@Column(name = "module")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Column(name = "submodule")
	public String getSubmodule() {
		return submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	@Column(name = "url_prefix")
	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	@Column(name = "subj_path")
	public String getSubjPath() {
		return subjPath;
	}

	public void setSubjPath(String subjPath) {
		this.subjPath = subjPath;
	}

	@Column(name = "link_path")
	public String getLinkPath() {
		return linkPath;
	}

	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}

	@Column(name = "date_path")
	public String getDatePath() {
		return datePath;
	}

	public void setDatePath(String datePath) {
		this.datePath = datePath;
	}

	@Column(name = "subj_replace")
	public String getSubjReplace() {
		return subjReplace;
	}

	public void setSubjReplace(String subjReplace) {
		this.subjReplace = subjReplace;
	}

	@Column(name = "date_replace")
	public String getDateReplace() {
		return dateReplace;
	}

	public void setDateReplace(String dateReplace) {
		this.dateReplace = dateReplace;
	}

	@ManyToOne
	@JoinColumn(name = "task_id")
	public CatchTask getTask() {
		return task;
	}

	public void setTask(CatchTask task) {
		this.task = task;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne
	@JoinColumn(name = "rule_id")
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("code=").append(code).append(";name=").append(name)
				.append(";province=").append(province).append(";department=")
				.append(department).append(";url=").append(url).append("<br/>");
		return buf.toString();
	}
}
