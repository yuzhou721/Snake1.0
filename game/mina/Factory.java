package game.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * 编解码工厂
 * Created by yuzhou721 on 2017/3/17.
 */
public class Factory implements ProtocolCodecFactory {
    private Decoder decoder;
    private Encoder encoder;

    public Factory(String charset) {
        this.decoder = new Decoder(Charset.forName(charset));
        this.encoder = new Encoder(Charset.forName(charset));
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
