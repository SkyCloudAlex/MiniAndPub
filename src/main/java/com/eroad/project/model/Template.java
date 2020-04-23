package com.eroad.project.model;

import java.util.List;

public class Template {

	// 消息接收openId
	private String toUser;
	// 模版编号
	private String templateId;
	// 消息链接
	private String url;
	// 消息顶部颜色
	private String topColor;
	// 参数列表
	private List<TemplateParam> templateParamList;
	
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopColor() {
		return topColor;
	}
	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}
	public List<TemplateParam> getTemplateParamList() {
		return templateParamList;
	}
	public void setTemplateParamList(List<TemplateParam> templateParamList) {
		this.templateParamList = templateParamList;
	}
	
	public String toJSON() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
		sb.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
		sb.append(String.format("\"url\":\"%s\"", this.url)).append(",");
		sb.append(String.format("\"topcolor\":\"%s\"", this.topColor)).append(",");
		sb.append("\"data\":{");
		
		for (int i = 0; i < templateParamList.size(); i++) {
			TemplateParam param = templateParamList.get(i);
			if (i == templateParamList.size() - 1) {
				sb.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(),
						param.getColor()));
			} else {
				sb.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(),
						param.getColor()));
			}
		}
		sb.append("}");
		sb.append("}");
		
		return sb.toString();
	}

}
