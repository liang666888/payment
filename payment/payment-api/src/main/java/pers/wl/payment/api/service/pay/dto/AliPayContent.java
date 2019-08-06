package pers.wl.payment.api.service.pay.dto;

/**
 * 
 * <p>File：BizContent.java</p>
 * <p>Title: 支付宝支付参数实体类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2017 上午11:36:38</p>
 * <p>Company: </p>
 * @author 吴亮
 * @version 1.0
 * 
 */
public class AliPayContent
{
    private String body;            // 对一笔交易的具体描述信息

    private String subject;         // 商品的标题(必填)

    private String out_trade_no;    // 商户网站唯一订单号

    private String total_amount;    // 订单总金额，单位为元，精确到小数点后两位(必填)

    private String product_code;    // 固定制QUICK_MSECURITY_PAY

    private String passback_params; // 回传参数

    public AliPayContent()
    {
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getOut_trade_no()
    {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no)
    {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount()
    {
        return total_amount;
    }

    public void setTotal_amount(String total_amount)
    {
        this.total_amount = total_amount;
    }

    public String getProduct_code()
    {
        return product_code;
    }

    public void setProduct_code(String product_code)
    {
        this.product_code = product_code;
    }

    public String getPassback_params()
    {
        return passback_params;
    }

    public void setPassback_params(String passback_params)
    {
        this.passback_params = passback_params;
    }
}
