/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年5月13日
 * @version: V1.0
 */

package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codingapi.tx.annotation.TxTransaction;
import com.codingapi.tx.config.service.TxManagerTxUrlService;

/**
 * @Description: TxManagerTxUrlServiceImpl
 * @author: Frankjiu
 * @date: 2020年5月13日
 */
@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {

	@Value("${tm.manager.url}")
	private String url;

	@Override
	public String getTxUrl() {
		return url;
	}

	@TxTransaction(isStart = true)
	//@Transactional
	public void trade() {
		//本地调用
		//tradeDao.save();
		//远程调用方
		//orderService.order();
	}

	//@Transactional
	@TxTransaction
	public void order() {
		//本地调用
		//orderDao.save();
	}

}
