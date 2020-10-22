package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.control.dto.FdtBackendResult;
import ch.mobi.ueliloetscher.learning.usermanagement.control.dto.FdtCodeWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.FdtDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.util.ClientRequest;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;

public class FdtGetService {

    @Inject
    private ClientRequest clientRequest;

    public Collection<FdtDTO> getCodes(int codeType) {
        FdtBackendResult result = getClient()
                .target("https://b2eapi1-t.mobiliar-int.ch/fdt_v2/rest/v4/bezeichnungen/arten/wertelisten?art="
                        + codeType
                        + "&metadaten=true&bezeichnungen=true")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", clientRequest.getAuthorizationHeader())
                .get(FdtBackendResult.class);

        Collection<FdtDTO> returnValue = new ArrayList<FdtDTO>();
        for (FdtCodeWrapper codeWrapper : result.getResults()) {
            returnValue.add(new FdtDTO(codeWrapper.getFdtCode().getArt().getArt(), codeWrapper.getFdtCode().getCode(), codeWrapper.getBezeichnung().getBezeichnungDE(), codeWrapper.getBezeichnung().getBezeichnungDE(), codeWrapper.getBezeichnung().getBezeichnungFR(), codeWrapper.getBezeichnung().getBezeichnungIT(), codeWrapper.getFdtCode().getGueltigAb(), codeWrapper.getFdtCode().getGueltigBis()));
        }
        return returnValue;
    }

    private Client getClient() {
        try {
            TrustManager[] trustManager = new X509TrustManager[]{new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                }
            }};

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, null);

            Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
