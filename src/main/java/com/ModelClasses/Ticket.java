package com.ModelClasses;

public class Ticket {
	private boolean forall;
	private boolean forold;
  private int[] users;
  private int noticket;

	public boolean getForall() {
		return forall;
	}
  public void setForall(boolean forall){
    this.forall = forall;
  }
  public boolean getForold(){
    return forold;
  }
  public void setForold(boolean forold){
    this.forold = forold;
  }
	public int[] getUsers() {
		return users;
	}
	public void setUsers(int users[]){
    this.users = users;
  }
  public int getNoticket(){
    return noticket;
  }
  public void setNoticket(int noticket){
    this.noticket = noticket;
  }



}
