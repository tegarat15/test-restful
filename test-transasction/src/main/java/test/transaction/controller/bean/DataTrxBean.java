package test.transaction.controller.bean;

import lombok.Data;

@Data
public class DataTrxBean {
	private double amount;
	private Long parentId;
	private String type;
}
