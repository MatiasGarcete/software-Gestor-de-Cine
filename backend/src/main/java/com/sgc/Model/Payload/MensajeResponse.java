package com.sgc.Model.Payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponse implements Serializable {
    private String mensaje;
    private Object objeto;
    /**** Nos devuelve un objeto:
     *    {
     *       "mensaje": "texto",
     *       "objeto": dato
     *    }
     * **/
}
