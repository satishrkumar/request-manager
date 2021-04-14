package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.service.RepaymentCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repayment")
public class RepaymentController {
    @Autowired
    RepaymentCalculatorService repaymentCalculatorService;
}
