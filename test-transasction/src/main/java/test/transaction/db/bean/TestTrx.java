package test.transaction.db.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "testtrx")
@Data
public class TestTrx {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "transaction_id", nullable = false)
	private Long transactionId;
	@Column(name = "parent_id")
    private Long parentId;
	@Column(name = "type", nullable = false)
    private String type;
	@Column(name = "amount", nullable = false)
    private Double amount;
    
	
}
