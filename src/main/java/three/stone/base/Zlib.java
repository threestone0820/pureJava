package three.stone.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.core.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Zlib {

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public final static String TAG = Zlib.class.getSimpleName();

    final static String nc2 = "{\"headers\":{\"key2\":\"\",\"compression\":[\"zlib\"],\"no_b64\":true},\"data\":{\"name\":\"wsHXm.0\"},\"method\":\"name_channel\"}";
    final static String keep_alive = "{\"method\":\"keep_alive\"}";
    final static String keep_alive2 = "{\"headers\":{\"route_num\":23,\"c\":{\"UDID\":\"\",\"iat\":\"\"},\"ua\":\"imoAndroid/7.3.4; 4.2.2; REL; GT-I9195; samsung; play; phone\"},\"data\":{\"ack\":2,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"dispatcher\"},\"data\":{\"data\":{\"ssid\":\"\"},\"method\":\"keep_alive\"},\"seq\":63,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String ack2 = "{\"data\":{\"ack\":2,\"ssid\":\"\",\"messages\":[]},\"method\":\"forward_to_server\"}";
    final static String cookie2 = "{\"headers\":{\"route_num\":1,\"c\":{\"UDID\":\"\",\"iat\":\"\"},\"ua\":\"imoAndroid/7.3.0; 4.1.2; REL; GT-I9300; samsung; play; phone\"},\"data\":{\"ack\":0,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"session\"},\"data\":{\"data\":{\"active\":false,\"ssid\":\"\",\"lang\":\"en-GB\"},\"method\":\"cookie_login\"},\"seq\":0,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String ka2 = "{\"data\":{\"ack\":1,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"dispatcher\"},\"data\":{\"data\":{\"ssid\":\"\"},\"method\":\"keep_alive\"},\"seq\":4,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String inv2 = "{\"data\":{\"ack\":4,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"monitor\"},\"data\":{\"data\":{\"events\":[{\"data\":{\"result\":\"SMS generic failure\",\"phone\":\"\",\"inviter_phone\":\"+\",\"is_resend\":true,\"data\":\" 1423871975351\",\"batch_size\":-1,\"num_tries\":2,\"message\":\"Let's video chat and text on imo! Get the free app http://imo.im\",\"clean_phone\":\"\",\"carrier_code\":\"\",\"inviter_uid\":\"\",\"errorCode\":28,\"delay\":474544,\"start_time_ms\":1423871975351,\"carrier_name\":\"VIVO\",\"phone_cc\":\"br\"},\"namespace\":\"sms_delivery\"}],\"ssid\":\"\"},\"method\":\"log_event\"},\"seq\":692,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String open = "{\"data\":{\"ack\":2,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"im\"},\"data\":{\"data\":{\"uid\":\"\",\"buid\":\"\",\"ssid\":\"\",\"proto\":\"imo\"},\"method\":\"open_chat\"},\"seq\":2,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String unread = "{\"data\":{\"ack\":1,\"ssid\":\"\",\"messages\":[{\"to\":{\"system\":\"im\"},\"data\":{\"data\":{\"uid\":\"\",\"ssid\":\"\",\"proto\":\"imo\"},\"method\":\"get_unread_msgs\"},\"seq\":1,\"from\":{\"system\":\"client\",\"ssid\":\"\"}}]},\"method\":\"forward_to_server\"}";
    final static String clientToServer = inv2 + open + keep_alive2 + keep_alive + unread + cookie2 + ka2 + nc2 + ack2;

    final static String nc = "{\"headers\":{\"heartbeat_new\":true,\"compression\":[\"zlib\"]},\"data\":{\"name\":\"Faster.1\"},\"method\":\"name_channel\"}";
    final static String ack = "{\"headers\":{\"http_headers\":{}},\"data\":{\"ack\":1,\"messages\":[]},\"method\":null}";
    final static String cookie = "{\"headers\":{\"http_headers\":{}},\"data\":{\"ack\":1,\"messages\":[{\"to\":{\"system\":\"client\",\"ssid\":\"2MdbftmOBo8bpV8sk\"},\"seq\":0,\"data\":{\"uid\":\"1007494312522139\",\"name\":\"signed_on\",\"edata\":{\"signup_date\":1424741225,\"alias\":\"Bbb\",\"is_email_verified\":false,\"premium\":false,\"profile_created\":true,\"inviter_client_select_all\":false,\"state\":\"active\",\"inviter_show_select_all\":false,\"verified_phone\":\"+16508629140\",\"premium_exp_date\":null,\"show_pin_tutorial\":false,\"username\":\"None\",\"premium_subscribed\":false,\"is_activated\":true,\"email\":null,\"invites_left\":0,\"max_points\":50,\"show_meet_new_people\":false,\"points\":50,\"is_phone_verified\":true,\"inviter_preselected\":10,\"phone_cc\":\"us\"},\"type\":\"account\",\"proto\":\"imo\"},\"from\":{\"system\":\"im\"}}]},\"method\":null}";
    final static String gotunread = "{\"headers\":{\"http_headers\":{}},\"data\":{\"ack\":2,\"messages\":[{\"to\":{\"system\":\"client\",\"ssid\":\"2MdbftmOBo8bpV8sk\"},\"seq\":1,\"data\":{\"uid\":\"1007494312522139\",\"name\":\"recv_unread_msgs\",\"edata\":{},\"type\":\"conv\",\"proto\":\"imo\"},\"from\":{\"system\":\"im\"}}]},\"method\":null}";
    final static String chatopen = "{\"headers\":{\"http_headers\":{}},\"data\":{\"ack\":7,\"messages\":[{\"to\":{\"system\":\"client\",\"ssid\":\"2MdbftmOBo8bpV8sk\"},\"seq\":4,\"data\":{\"uid\":\"1007494312522139\",\"name\":\"chat_opened\",\"edata\":{\"buid\":\"1007494312522139\"},\"type\":\"conv\",\"proto\":\"imo\"},\"from\":{\"system\":\"im\"}}]},\"method\":null}";
    final static String serverToClient = chatopen + gotunread + cookie + ack + keep_alive + nc;

    final static String avPushToClient = "{\"uid\":\"3035011294544282\",\"edata\":{\"buid\":\"1126717815949476\",\"cbc_ticket\":\"4H9uterdCv/CW54ML6F2Oop90hAeob64IDNOv+4ORXIU1Hp2zxKoiZ47KL1fMK5eTYu/jPNdT225KHF/HteF/T9DJUEi0bjMLui/ufjFqbUUeqPTWu2wJOE1hwCBTZT1+lcS37CSno6P8ve0RkvEgBhrKVzyXPdkuuYajyhJa87urXkvQnrO/6LOpMcgUzq/MCAJx+0tI53wXaGAX98H1gTRgPoxs0yaqN/5gQ8syvDsd/re4GsK1bSBoaasoQLyKPmsMU3t42hu3MKZUH6VSPbPolwqpXx9c16Lzwdh0TJDSf3z8/fS4RHXoiGMeTbdjeE=\",\"av_sync_params\":[200,200,200,0,100,20,20,63,200,200,1],\"icon\":\".8PeRjiRzfqzAUasGLiLoLzENuqR\",\"video_arq_params\":[9,9,940,10000,3,450,450,450],\"server_ticket\":\"DrKkRZ6qTfWKXHclEKjD/N8yYaRz/V/wAZzkO1yczlE3wxAAyUhFWpQrcDRyFci7rPdn9W3L+KGYtN+g+v6faNaXQetsIWLvrKNH9xGki12J5X1lOMSTVZ6lsjMmNEX0CoS5UGvmYH86R3l04pF4OchlDHPYqDDSALcwOInoounsJdcbct4VV5enKWvCy8cuh6B8WwqnCaHBWQhn71CpjYXDNhKBW4hhVcZqwG75ylISviazFhtW8EdHqHXxpn2X\",\"client_type\":\"macaw\",\"ab_str\":\"AAAAAAAAAAAAAAAAAAAAAAAABAAAAIAAAAAAAAAAQQIjAAAAACAgBptSxGSBARcAAABBBAQhC5GjKxk3E1JAeQ==\",\"server_key\":\"6lUWmfBUWpPTfh+YEYXw6DA6VjFSMDJnV2Z0bVY4RGM=\",\"ab_first\":0,\"client_id\":0,\"error_correction_allowed\":true,\"chat_type\":\"video_chat\",\"error_correction_params\":[0.05,1,6144],\"req_id\":221104162928107503,\"quality_config\":[[64,160,224],[120,240,320],[200,336,416],[280,400,512],[360,1400,640]],\"buddy_alias\":\"R.P. sunam\",\"uplink_bandwidth_estimates\":{},\"hd_bit_params\":[16000,16000],\"is_initiator\":true,\"pipe\":{\"port\":443,\"ip\":\"35.244.41.148\",\"ports_udp\":[7421],\"conv\":\"0:V1R02gWftmV8DcTP#\",\"port_udp\":7421},\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19691,21691,0,8,0,0,0,0],\"buddy\":{},\"audio_stats_params\":[-1],\"poor_network_params\":[80,3,1200,5000,20],\"pipes\":[{\"port\":7421,\"ip\":\"35.244.41.148\",\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19691,21691,0,8,0,0,0,0]},{\"tickets\":[\"POlVkv65wDRQqCFXrx+oI4a2Cp86fsemLaCbafb/CO4av0IoYfx4+oXiCEeZFfCSTktS/OYbtjinl4hlXIowZwi7UFwuUtLSyFLHDsI9cBhxXlMC41xo2p2b0siOA+QlZ5KYfYl2iUErvNjkkEQll857S9a93eyiLA9OjwHKjYkn7Z5bR0zvcfUFIvsdwmq39GZPQ4yWNhPhw7h/6tLN0ihC+Pp1jNTy9dTgUSozmRrwSQDSRNKYCYf9hZHBtoPGhrdJlOCo3EwZ2Q5IT4CjdDUAty2pqmutlBsVHiQIBpbWcCgoaysU3Y04RC/ClTrS0D6jjGY=\"],\"port\":16995,\"ip\":\"34.93.4.101\",\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19690,21690,1,0,7,1,1,1]},{\"port\":29434,\"ip\":\"35.200.129.160\",\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19689,21689,1,0,7,1,1,1]},{\"tickets\":[\"zKBt6pHwyYT56P+Sbk6a3+KC1Tt5YH3xlYCwsUKV3pdNUbvw+yV7Eup3cpu5xPg+UQ/1mojWqnEO3WVS4FEccHGNV997os+P2OPem6CGNkwyNzWk73V/ppfWP5ZBxg0tGMxM6bm/EKEXwvAR0OLfKHRchw+0mek/IGCKAHJcy+7pvuOtt+LaQmy25xAAJbAx/PQPgw/nRLqPCHSD6/PfOzB0oWD5vOMwr4Wob+UxRwcxQ5jp8mFPe6d43iGW3CF7lV7buOGjfup334aQvjAYfT/u24oNyE3CFQk4XdaXxLtCVjkooLLa0RJfKfkW3nDR85uraJoGHf7qC5RfXfbk\"],\"port\":16174,\"ip\":\"34.93.185.144\",\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19688,21688,2,1,1,0,0,0]},{\"port\":32460,\"ip\":\"34.93.74.242\",\"net\":[497,497,20,0,516,0,750,2000,500,2000,250,0,19695,21695,2,1,1,0,0,0]}],\"audio_retransmit_params\":[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,30,50,13,13,400,1],\"cbc_key\":\"/u//H5D/1+pxgkssVFDF0Q==\",\"bit_params\":[1,1,1,0,1.2,1.2,20,20,1.0E16,0,300,15,60000,0.05,1.2,10,1.5,1500,1,250,12000,8000,30,0,20,-1,0,0,0,2,4,0.6,0.6],\"is_h264\":null,\"shared_key\":\"NNLZUZfxj6Qv/aOkhg8Tl3wH7pkGOjkuQsQoQtuTl2I=\",\"max_video_bitrate\":239,\"call_params\":[70,20],\"timestamp_nano\":1667558666438000128,\"peer_cbc_key\":null},\"push_id\":\"0:V1R02gWftmV8DcTP#@\",\"proto\":\"imo\",\"name\":\"streams_info\",\"type\":\"av\"}{\"uid\":\"1107887729251137\",\"edata\":{\"buid\":\"3033135361726817\",\"cbc_ticket\":\"gygOuuzsKBygzFX6oEgNdccdk25bdSs3iWkPfGkAgvZ+3w5+kyzktyLzqeAED2S2GDALmF60Yeabv6hettWyvt7Lps5bZwtjc5AUHQYxwgYxunsVhdxEkKyfJ2GUhMz3DZ36BLS+N/t1pQYxYrzbb75F0R4/EKs4aTs0V+lRFkm6dVzyY3A19zu68yc4S6CnIzao9ciuFCTAuKiOxWNpr213JGmH4ztXY865H1GvdnB0gEk9kI0vR2EWyWA9Y9Obiq7oiCeoQ8tWvLNHCC79WCIBIYDz8VTKCa/4cOhFd3Z5rBs2z3LwSMvLsNzSDrkv+7hDR7Rr5joIJYKOP1YyRVgpsYdcDdDdlMrT8NK4mQX+GDGy11q8I17WrTtwh9/+p+nf72ZzywLDSOiQfEKiU/0O333iCQcfR4O83F67uvAfK6cpyCcxwAX1CKLnYlY3ewy5rtsZ8I0iGTbcOOTK8sqjlHGRV19au6eYISsc7ftONEuxfjlQoWMh1UkFNlKvwj5Habil8YCSwbqB161aRaEh19gLJYTOOKcby/d1xNRriPXli0kGBWjR\",\"icon\":\".8NVExITbNncDmFQfJCXTlNlZPJf\",\"server_ticket\":\"DvEnINJ0ijl8/8pRp07bAJ943j1YSSLXFA4vA875f/waYomEotytJWckEWrc6jDoXEJ0YxNyj246dOJxzG45S0F5vjF4i6WazGAc0eKp8955coUTqltC6QElzb7dGfOModdbTMOsEkLbzi914oAVn8Z96fsLkuE1sK575mbLaj22ZHclGD6f7MXNvVTjqKvdAmt6OD5SmJ3DiHFL4OKiR7P97bRNbtPmY2VA4sSlY6J6f9J57sPrIuxbrl/PbKSRVDgooA4AJdM+j9HNQB+hHEBBrtHsEO47XOzHn3kX8AHP6pSw4VQSJjPw+Csq+j3c8YsWkwC2b40Mt61wtHCKk0Eg9yDVSwt/SCCDKjpq+UopOJHs7H8lSmpaS09Ollh5EHVP0DzzwJlZ5gGtmumKTyT+UUJlSxLz3OHCkEg4Yddxd8nXBVzVjGFxjyjZxovgdPEviK79ldtI1mIrIYtUebRMt7cjaiUbRpPFooBqdsumj9sJA43k78Kiu8WAjC8Dhcr0wLTkzcLxEp0VxoSQIUARrFPd8gFvN/Fybb2HbTo1pyNbnt1yTG7+mUnAAg==\",\"client_type\":\"macaw\",\"ab_str\":\"AAAAAAAAAAAAAAAAAAAAAAAABgAQAIAAAAAAACCAQAIAAoAAACAAABITZHeFAQICCAAABKQgKoGjCAkUAEIAeQ==\",\"server_key\":\"6lUWmfBUWpPTfh+YEYXw6DA6QTFGMTJzSXBJUEhHTVE=\",\"ab_first\":0,\"client_id\":0,\"error_correction_allowed\":true,\"chat_type\":\"audio_chat\",\"error_correction_params\":[0,1,6144,-1,-1,-1,-1,-1,-1,-1,1,-1,0.05],\"req_id\":221104134737292036,\"quality_config\":[[64,160,224],[120,240,320],[200,336,416],[280,400,512],[360,1400,640]],\"buddy_alias\":\"ابو بلال العلواني\",\"uplink_bandwidth_estimates\":{},\"hd_bit_params\":[16000,16000],\"is_initiator\":true,\"pipe\":{\"port\":443,\"ip\":\"169.136.152.56\",\"ports_udp\":[45824],\"conv\":\"0:A1F12sIpIPHGMQuJ#\",\"port_udp\":45824},\"net\":[0,0,0,0,1000,1000,0,0,0,0,0,-252,0,0],\"buddy\":{},\"audio_stats_params\":[-1],\"pipes\":[{\"port\":45824,\"ip\":\"169.136.152.56\",\"net\":[0,0,0,0,1000,1000,0,0,0,0,0,-252,0,0]},{\"tickets\":[\"N/dsOxPTO3c6o5vrCJtvF4e3gzgOd6NPVTk0lE2w0OD1Vot6DRk42V0a6rHHozgGq2uhAAOiBXLknJE9Qxw8ESjF2/4+BeufpoAVt8a6/sjqC0ReS9WbPvdL5fsPtFPKD8GQJb5PPUF/3n4RHr4JmG9/dtD+3HlWllenkCyDvI9EHl2siFD/XWKFE2h2x6UDt6jWmnDeVsh0vE6JVrilZnyzAhaHDvSQXcoofSnUhPo30eLqwzAiDffTu306SZEInqA7wrkRc+xAp5L3z4L6NAXjz9CGE/RFLiG+w90FZPdIejmKnWEMBQP6BHziLbCNxQXFNz9cv9meEDcWv68t6L8WYvnnom+BU4FKehfI/cRTLtNlzOHAWPhXafWhd1ffUF85GjvHcX1sGusWd4zQBhz5iE5Lval5wnRwWFNOdRqAS4TYeATLv359Vi5Qv8JYcT+IhgFurj/RdAtI3WRtQW30XxufQs2lw+yXYSkSLpgW5JzsRRSTBbiwKqPngOA3Xl3jwY1IrYibC8ne4rOmMOvzb0CiQr8PC5Uo3x/Wd0Uihuy7DvYRyltMFXJWWy+ODtZGOb9UmSnUhAaCWnB10XfbP0L87qg1eFKODGOnc80J3mAxg0tvEYHwU8BCrEhA/LfYZBUU6BVEdXcGfsad8i0OIkcPYhXPqY5ZPNl3uMylW+h5Yq2purQ7qf9QoKxnqX7oEM9wXaQn4vSiVB5LCtdHMMXD+1hEh5MXKezC/hc+6EDhR+UJRxnBufm4\"],\"port\":8083,\"ip\":\"169.136.152.62\",\"net\":[37,41,8,18,500,508,0,0,0,5000,500,256,-3465,-3465,1,1,1,1,1,1]},{\"port\":81,\"ip\":\"169.136.152.74\",\"net\":[37,41,8,18,500,508,0,0,0,5000,500,152,0,0,1,1,1,1,1,1]}],\"audio_harq_params\":[-1,-1,-1,-1,-1,0.05,500,-1,0],\"audio_retransmit_params\":[-1,100,800,-1,-1,-1,-1,-1,-1,200,3,1],\"cbc_key\":null,\"bit_params\":[64,64,400,0.002,0.9,0.98,20,30,1.0E16,0,100,4,60000,0.05,1.2,10,1.5,1500,1,250,12000,8000,30,0,20,-1,0,0,0,2,4,0.6,0.6],\"is_h264\":null,\"shared_key\":\"6ua8G05qm9FaE/UkEpFPiwOlv03JftGYQ8Ob5BOgNco=\",\"max_video_bitrate\":180,\"call_params\":[70,20],\"timestamp_nano\":1667558824595000064,\"peer_cbc_key\":null},\"push_id\":\"0:A1F12sIpIPHGMQuJ#@\",\"proto\":\"imo\",\"name\":\"streams_info\",\"type\":\"av\"}";

    static byte[] serverToClientBytes = null;
    static byte[] clientToServerBytes = null;
    static byte[] avPushToClientBytes = null;

    final static int tempSize = 15000000;
    static byte[] temp = new byte[tempSize];
    static byte[] out = new byte[tempSize];

    static {
        try {
            serverToClientBytes = serverToClient.getBytes("UTF-8");
            clientToServerBytes = clientToServer.getBytes("UTF-8");
            avPushToClientBytes = avPushToClient.getBytes("UTF-8");
        } catch (Exception e) {
        }
    }

    static Inflater inflater = new Inflater();
    static Deflater deflater = new Deflater();

    public static byte[] compressWithDict(final byte[] data) {
        deflater.reset();
        deflater.setInput(data);
        deflater.setDictionary(serverToClientBytes);
        deflater.finish();
        int index = 0;
        while (!deflater.finished()) {
            int count = deflater.deflate(out, index, tempSize);
            index += count;
        }
        return Arrays.copyOf(out, index);
    }

    public static byte[] decompressWithDict(final byte[] data)
            throws IOException, DataFormatException {
        inflater.reset();
        inflater.setInput(data);
        int index = 0;
        while (!inflater.finished()) {
            int count = inflater.inflate(out, index, tempSize);
            if (count == 0 && inflater.needsDictionary()) {
                inflater.setDictionary(clientToServerBytes);
            } else if (count == 0 && !inflater.finished()) {
                throw new IOException("inflate returned 0");
            }
            index += count;
        }
        return Arrays.copyOf(out, index);
    }

    public static String compressWithDict(final String payload) throws UnsupportedEncodingException, IOException {
        byte[] data = payload.getBytes("UTF-8");
        deflater.reset();
        deflater.setInput(data);
        deflater.setDictionary(serverToClientBytes);
        deflater.finish();
        int index = 0;
        while (!deflater.finished()) {
            final int count = deflater.deflate(out, index, tempSize);
            index += count;
        }

        byte[] bytes = new byte[index];
        System.arraycopy(out, 0, bytes, 0, index);
        return new String(Base64.encode(bytes), "UTF-8");
    }

    public static byte[] decompressWithAVPushDict(final byte[] data)
            throws IOException, DataFormatException {
        inflater.reset();
        inflater.setInput(data);
        int index = 0;
        while (!inflater.finished()) {
            int count = inflater.inflate(out, index, tempSize);
            if (count == 0 && inflater.needsDictionary()) {
                inflater.setDictionary(avPushToClientBytes);
            } else if (count == 0 && !inflater.finished()) {
                throw new IOException("inflate returned 0");
            }
            index += count;
        }
        return Arrays.copyOf(out, index);
    }

    public static String compressWithAVPushDict(final String payload) throws UnsupportedEncodingException, IOException {
        byte[] data = payload.getBytes("UTF-8");
        deflater.reset();
        deflater.setInput(data);
        deflater.setDictionary(avPushToClientBytes);
        deflater.finish();
        int index = 0;
        while (!deflater.finished()) {
            final int count = deflater.deflate(out, index, tempSize);
            index += count;
        }

        byte[] bytes = new byte[index];
        System.arraycopy(out, 0, bytes, 0, index);
        return new String(Base64.encode(bytes), "UTF-8");
    }

    public static String decompressWithDict(final String msg) throws IOException, DataFormatException {
        byte[] data = Base64.decode(msg);
        inflater.reset();
        System.arraycopy(data, 0, temp, 0, data.length);
        inflater.setInput(temp, 0, data.length);
        int index = 0;
        while (!inflater.finished()) {
            int count = 0;
            try {
                count = inflater.inflate(out, index, tempSize);
            } catch (DataFormatException e) {
                throw e;
            }
            if (count == 0 && inflater.needsDictionary()) {
                inflater.setDictionary(clientToServerBytes);
            } else if (count == 0 && !inflater.finished()) {
                throw new IOException("inflate returned 0");
            }
            index += count;
        }
        byte[] bytes = new byte[index];
        System.arraycopy(out, 0, bytes, 0, index);
        return new String(bytes, "UTF-8");
    }

    public static byte[] compress(byte[] data) throws UnsupportedEncodingException, IOException {
        deflater.reset();
        deflater.setInput(data);
        deflater.finish();
        int index = 0;
        while (!deflater.finished()) {
            final int count = deflater.deflate(out, index, tempSize);
            index += count;
        }

        byte[] bytes = new byte[index];
        System.arraycopy(out, 0, bytes, 0, index);
        return bytes;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        inflater.reset();
        System.arraycopy(data, 0, temp, 0, data.length);
        inflater.setInput(temp, 0, data.length);
        int index = 0;
        while (!inflater.finished()) {
            int count = 0;
            try {
                count = inflater.inflate(out, index, tempSize);
            } catch (DataFormatException e) {
                throw e;
            }
            if (count == 0 && !inflater.finished()) {
                throw new IOException("inflate returned 0");
            }
            index += count;
        }
        byte[] bytes = new byte[index];
        System.arraycopy(out, 0, bytes, 0, index);
        return bytes;
    }

    public static void main(String[] args) throws IOException, DataFormatException {
        // 后台压缩过程：
        Map<String, Object> data = new HashMap<>();
        data.put("server_key", "6lUWmfBUWpPTfh+YEYXw6DEyNzY6QTJGMDQxcXJ4Q00=");
        data.put("icon", ".GDIOJmcoLljdBnqueOFJGRWGY0m");
        data.put("shared_key", "zpiuGt9GganlrDoKGnCzWqNhXBKjki9vfYB5adQe7q4=");
        String d = gson.toJson(data);
        String compressedData = Zlib.compressWithAVPushDict(d);

        // 客户端解压缩过程：
        byte[] base64Decoded = Base64.decode(compressedData);
        byte[] zlibDecompress = decompressWithAVPushDict(base64Decoded);
        String json = new String(zlibDecompress, "UTF-8");
        Map map = gson.fromJson(json, Map.class);
        System.out.println(map);


    }
}
