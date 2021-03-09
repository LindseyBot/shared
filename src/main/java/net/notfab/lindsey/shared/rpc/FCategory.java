package net.notfab.lindsey.shared.rpc;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FCategory extends FChannel {

    private List<FChannel> channels;

}
