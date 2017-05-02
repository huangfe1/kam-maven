package com.dreamer.view.user;

import com.dreamer.domain.account.LogistFeeRecord;
import com.dreamer.domain.user.User;
import com.dreamer.repository.account.LogistFeeRecordDAO;
import com.dreamer.repository.user.MutedUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ps.mx.otter.utils.SearchParameter;
import ps.mx.otter.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/logistFee")
public class LogistFeeRecordController {

	@RequestMapping(value = "/record.html", method = RequestMethod.GET)
	public String index(
			@ModelAttribute("parameter") SearchParameter<LogistFeeRecord> parameter,
			Model model, HttpServletRequest request) {
		try {
			List<LogistFeeRecord> pts;
			User user = (User) WebUtil.getCurrentUser(request);
			if (user.isAdmin()) {
				pts = logistFeeRecordDAO.searchEntityByPage(parameter);
			} else {
				pts = logistFeeRecordDAO.searchEntityByPage(parameter, user);
			}
			WebUtil.turnPage(parameter, request);
			model.addAttribute("pts", pts);
			model.addAttribute("from", user.getId());
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return "user/logist_fee_record";
	}
	
	@ModelAttribute("parameter")
	public SearchParameter<LogistFeeRecord> preprocess(
			@RequestParam("id") Optional<Integer> id) {
		SearchParameter<LogistFeeRecord> parameter = new SearchParameter<LogistFeeRecord>();
		if (id.isPresent()) {
			//parameter.setEntity(LogistFeeRecordDAO.findById(id.get()));
		} else {
			parameter.setEntity(new LogistFeeRecord());
		}
		return parameter;
	}
	

	@Autowired
	private MutedUserDAO mutedUserDAO;
	@Autowired
	private LogistFeeRecordDAO logistFeeRecordDAO;
}
