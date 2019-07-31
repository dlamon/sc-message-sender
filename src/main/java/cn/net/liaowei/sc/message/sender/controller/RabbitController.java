package cn.net.liaowei.sc.message.sender.controller;

import cn.net.liaowei.sc.message.common.CommonDTO;
import cn.net.liaowei.sc.message.common.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    private AmqpTemplate amqpTemplate;

    public RabbitController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @PostMapping("/common/send")
    public void commonSend(@RequestBody CommonDTO commonDTO) {
        log.info("Common send: {}", commonDTO);
        amqpTemplate.convertAndSend("commonTopic", commonDTO);
    }

    @PostMapping("/order/send")
    public void orderSend(@RequestBody OrderDTO orderDTO) {
        log.info("Order send: {}", orderDTO);
        amqpTemplate.convertAndSend("orderTopic", orderDTO.getOrderType(), orderDTO);
    }
}
