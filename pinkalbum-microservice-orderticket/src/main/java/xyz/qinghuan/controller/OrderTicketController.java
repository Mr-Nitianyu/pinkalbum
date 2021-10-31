package xyz.qinghuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qinghuan.service.OrderTicketService;
import xyz.qinghuan.vo.SysResult;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order/manage")
public class OrderTicketController {
    @Autowired
    private OrderTicketService orderTicketService;

    /**
     * 获取订课网站学院参数
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/fetchinfo")
    public SysResult fetchinfo(String account, String password, String teacher, String phoneNum) throws IOException {
        int stat = orderTicketService.fetchinfo(account, password, teacher, phoneNum);
        SysResult result = new SysResult();
        if (stat == 0) {
            result.setStatus(300);
            result.setMsg("手机号必须为注册手机号");
        } else if (stat == 2) {
            result.setStatus(300);
            result.setMsg("未查询到教练信息");

        } else if (stat == 3) {
            result.setStatus(400);
            result.setMsg("驾校暂时未安排教练，等安排后再进行补录抢课");
        } else {
            result = SysResult.ok();
        }
        return result;
    }

    /**
     * 添加课程预约时间
     *
     * @param orderTimes
     * @return
     */
    @RequestMapping("/addOrderTime")
    public SysResult addOrderTime(String orderTimes, String cookieStat, String orderDate) throws IOException {
        int stat = orderTicketService.addOrderTime(orderTimes, cookieStat, orderDate);
        SysResult result = new SysResult();
        if (stat == 000) {
            result = SysResult.ok();
        } else {
            result.setStatus(300);
            result.setMsg("添加失败");
        }
        return result;
    }

    /**
     * 添加课程预约时间
     *
     * @param orderTimes
     * @return
     */
    @RequestMapping("/deleteOrderTime")
    public SysResult deleteOrderTime(String orderTimes, String cookieStat, String orderDate) throws IOException {
        int stat = orderTicketService.deleteOrderTime(orderTimes, cookieStat, orderDate);
        SysResult result = new SysResult();
        if (stat == 000) {
            result = SysResult.ok();
        } else {
            result.setStatus(300);
            result.setMsg("取消失败");
        }
        return result;
    }

    /**
     * 查询课程预约时间
     *
     * @param cookieStat
     * @param orderDate
     * @return
     */
    @RequestMapping("/queryOrderTime")
    public SysResult queryOrderTime(String cookieStat, String orderDate) throws IOException {
        List orderTime = orderTicketService.queryOrderTime(cookieStat, orderDate);
        SysResult result = new SysResult();
        if (orderTime.size() > 0) {
            result.setStatus(200);
            result.setData(orderTime);
        } else {
            result.setStatus(300);
            result.setMsg("添加失败");
        }
        return result;
    }

    /**
     * 查询驾校信息
     *
     * @param stat
     * @return
     */
    @RequestMapping("/queryfetchinfo")
    public SysResult queryfetchinfo(String stat) throws IOException {
        List fetchinfo = orderTicketService.queryfetchinfo(stat);
        SysResult result = new SysResult();
        if (fetchinfo.size() > 0) {
            result.setStatus(200);
            result.setData(fetchinfo);
        } else {
            result.setStatus(300);
            result.setMsg("添加失败");
        }
        return result;
    }
}
