public class Request {
    private String reqMessage;
    private String cid;
    private int num_req;
    Request(String reqMessage){
        this.reqMessage = reqMessage;
        this.cid = Client.cid;
        this.num_req = Client.num_req;
    }

    public String getReq(){
        return "request"+"///"+reqMessage+"///"+"CID:"+this.cid+"///"+"Num_Req:"+this.num_req+"///"+"END_MSG";
    }
    public String getReqMessage(){
        return this.reqMessage;
    }
    public int getNumReq(){
        return this.num_req;
    }
}
