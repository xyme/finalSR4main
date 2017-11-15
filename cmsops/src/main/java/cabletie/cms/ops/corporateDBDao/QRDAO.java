package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.QR;

@Repository
public interface QRDAO extends JpaRepository<QR, String>{
	
	List<QR> findByqrcode(String QRCODE);

}
