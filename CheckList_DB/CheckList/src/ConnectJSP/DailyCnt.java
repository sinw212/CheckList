package ConnectJSP;

import ConnectDB.Daily;

public class DailyCnt {
	private Daily dailyCnt = Daily.getInstance();

	private String type;
	private String date;
	private String check;
	private String todo;
	
	private String result;
	
	public DailyCnt(String type, String date, String check, String todo) {
		this.type = type;
		this.date = date;
		this.check = check;
		this.todo = todo;
		System.out.println("Type : "+ type);
		
		if(type.equals("todoShow")) {
			todoShowCheck(date);
		}
		else if(type.equals("todoAdd")) {
			todoAddCheck(date, check, todo);
		}
		else if(type.equals("checkModify") ) {
			checkModifyCheck(date, check, todo);
		}
		else if(type.equals("todoDelete")) {
			todoDeleteCheck(date, todo);
		}
		else {
			todoError();
		}
	}
	
	public void todoShowCheck(String date) {
		result = dailyCnt.todoShow(this.date);
	}
	
	public void todoAddCheck(String date, String check, String todo) {
		result = dailyCnt.todoAdd(this.date,  this.check,  this.todo);
	}
	
	public void checkModifyCheck(String date, String check, String todo) {
		result = dailyCnt.checkModify(this.date, this.check, this.todo);
	}
	
	public void todoDeleteCheck(String date, String todo) {
		result = dailyCnt.todoDelete(this.date, this.todo);
	}
	
	public void todoError() {
		
	}
	
	public String getResult() {
		return result;
	}
}