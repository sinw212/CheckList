package ConnectJSP;

import ConnectDB.Goal;

public class GoalCnt {
	private Goal goalCnt = Goal.getInstance();
	
	private String type;
	private String date;
	private String goal;
	
	private String result;
	
	public GoalCnt(String type, String date, String goal) {
		this.type = type;
		
		if(type.getBytes().equals("goalShow")) {
			goalShowCheck(date);
		}
		else if(type.getBytes().equals("goalModify")) {
			goalModifyCheck(date, goal);
		}
		else {
			todoError();
		}
	}
	
	public void goalShowCheck(String date) {
		result = goalCnt.goalShow(this.date);
	}
	
	public void goalModifyCheck(String date, String goal) {
		result = goalCnt.goalModify(this.date,  this.goal);
	}
	
	public void todoError() {
		
	}
	
	public String getResult() {
		return result;
	}
}