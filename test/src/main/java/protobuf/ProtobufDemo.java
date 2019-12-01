package protobuf;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ProtobufDemo {

    /**
     * protobuf 为每一个序列化对象提供了一个POJO和Bulider
     *
     * @return
     */
    public static MsgProtos.Msg buildMsg() {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(1000);
        builder.setContent("1234");
        builder.addList("123");
        builder.addList("456");
        MsgProtos.Msg msg = builder.build();
        return msg;
    }


    public static void main(String[] args) throws IOException {
        MsgProtos.Msg msg = buildMsg();
        byte[] bytes = msg.toByteArray();
        System.out.println(bytes.length);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        outputStream.write(bytes);
//        bytes = outputStream.toByteArray();

        MsgProtos.Msg inmsg = MsgProtos.Msg.parseFrom(bytes);
        System.out.println(inmsg.getId());
        System.out.println(inmsg.getContent());
        List<String> a = inmsg.getListList();
        for (String s : a){
            System.out.println(s);
        }
        System.out.println(inmsg.getList(0));
        System.out.println(inmsg.getList(1));
    }

    @Test
    public void test() throws IOException {
        MsgProtos.Msg message = buildMsg();
        //序列化到二进制流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        //从二进流,反序列化成Protobuf 对象
        MsgProtos.Msg inMsg = MsgProtos.Msg.parseFrom(inputStream);
        System.out.println("id:=" + inMsg.getId());
        System.out.println("content:=" + inMsg.getContent());
    }
}
