package test.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import test.transaction.controller.bean.DataTrxBean;
import test.transaction.process.TestProcess;

@RestController
@RequestMapping("/transactionservice")
@Api(description = "List of Transaction Services", tags = { "Transaction" })
public class Controller {
	
	@Autowired
	TestProcess process;
	
	@GetMapping("/")
	public HttpEntity test() {
	  return new ResponseEntity<>("Service is up", HttpStatus.OK);
	}
	
	@GetMapping("/transaction/{transactionId}")
	public HttpEntity getTransaction(@PathVariable(value = "transactionId") Long trxId) {
	  return new ResponseEntity<>(process.getByTransactionId(trxId), HttpStatus.OK);
	}
	
	@GetMapping("/type/{type}")
	public HttpEntity getType(@PathVariable(value = "type") String type) {
	  return new ResponseEntity<>(process.getByType(type), HttpStatus.OK);
	}
	
	@GetMapping("/sum/{transactionId}")
	public HttpEntity getSumTransaction(@PathVariable(value = "transactionId") Long trxId) {
	  return new ResponseEntity<>(process.sumByTransactionId(trxId), HttpStatus.OK);
	}
	
	@PutMapping("/transaction/{transactionId}")
	public HttpEntity<?> placeOrder(@PathVariable("transactionId") Long trxId, @RequestBody DataTrxBean dataTrx) {
		boolean insert = process.insertUpdateData(trxId, dataTrx);
		if(insert) {
			return new ResponseEntity<>("Data has been inserted", HttpStatus.OK);
		}else{
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
