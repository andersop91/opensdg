package org.opensdg;

public class OpenSDG {
    static {
        System.loadLibrary("opensdg_jni");
    }

    public static native void SetPrivateKey(byte[] key);

    public static native byte[] GetMyPeerId();

    public static native byte[] CreatePrivateKey();

    native static long connection_create(OSDGConnection osdgConnection);

    native static void connection_destroy(long conn);

    native static int connect_to_danfoss(long conn);

    native static int connect_to_remote(long grid, long peer, byte[] peerId, String protocol);

    native static int pair_remote(long grid, long peer, String otp);

    native static int connection_close(long conn);

    native static int send_data(long conn, byte[] data);

    native static void set_blocking_mode(long conn, boolean blocking);

    native static boolean get_blocking_mode(long conn);

    native static int get_connection_state(long conn);

    native static int get_last_result(long conn);

    native static int get_last_errno(long conn);

    native static byte[] get_peer_id(long conn);

    native static int set_ping_interval(long conn, int seconds);
}