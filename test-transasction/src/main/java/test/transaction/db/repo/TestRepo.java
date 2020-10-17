package test.transaction.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.transaction.db.bean.TestTrx;

@Repository
public interface TestRepo extends CrudRepository<TestTrx, Long>{
	@Query(value = "select id, transaction_id, parent_id , type, amount from testtrx where transaction_id = ?1", nativeQuery = true)
    List<TestTrx> findByTransactionId(Long trxId);
	
	@Query(value = "select id, transaction_id, parent_id , type, amount from testtrx c where c.type = ?1", nativeQuery = true)
    List<TestTrx> findByType(String type);
	
	@Query(value = "select sum(amount) from testtrx where parent_id = (select parent_id from testtrx where transaction_id = ?1)", nativeQuery = true)
	Double sumAmountByTransactionId(Long trxId);
}
