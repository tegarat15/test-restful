package test.transaction.process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.transaction.controller.bean.DataTrxBean;
import test.transaction.controller.bean.SumBean;
import test.transaction.db.bean.TestTrx;
import test.transaction.db.repo.TestRepo;

@Service
public class TestProcess {
	@Autowired
	TestRepo testRepo;
	
	public DataTrxBean getByTransactionId(Long trxId) {
		DataTrxBean data = new DataTrxBean();
		List<TestTrx> lisTrx = testRepo.findByTransactionId(trxId);
		if(null!=lisTrx && !lisTrx.isEmpty()) {
			data.setAmount(lisTrx.get(0).getAmount());
			data.setParentId(lisTrx.get(0).getParentId());
			data.setType(lisTrx.get(0).getType());
		}
			
		return data;
	}
	
	public List<Long> getByType(String type) {
		List<TestTrx> listTrx = testRepo.findByType(type);
		List<Long> listTrxId = new ArrayList<Long>();
		if(null!=listTrx && !listTrx.isEmpty()) {
			listTrxId = listTrx.stream().map(x->x.getTransactionId()).collect(Collectors.toList());
		}
		return listTrxId;
	}
	
	public SumBean sumByTransactionId(Long trxId) {
		SumBean sum = new SumBean();
		sum.setSum(testRepo.sumAmountByTransactionId(trxId));
		return sum;
	}
	
	public boolean insertUpdateData(Long trxId, DataTrxBean data) {
		try {
			TestTrx dataTrx = new TestTrx();
			List<TestTrx> lisTrx = testRepo.findByTransactionId(trxId);
			if(null!=lisTrx && !lisTrx.isEmpty()) {
				dataTrx = lisTrx.get(0);
			}
			dataTrx.setTransactionId(trxId);
			dataTrx.setAmount(data.getAmount());
			dataTrx.setParentId(data.getParentId());
			dataTrx.setType(data.getType());
			testRepo.save(dataTrx);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
}
