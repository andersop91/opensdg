package org.opensdg;

public class OpenSDG {
    static {
        System.loadLibrary("opensdg_jni");
        int res = init();
        if (res != 0) {
            throw new ExceptionInInitializerError("opensdg library initialization failed");
        }
    }

    public static class Version {
        public int major;
        public int minor;
        public int patch;

        Version(long code) {
            major = (int) ((code >> 32) & 0x0FFFF);
            minor = (int) ((code >> 16) & 0x0FFFF);
            patch = (int) (code & 0x0FFFF);
        }

        @Override
        public String toString() {
            return major + "." + minor + "." + patch;
        }
    };

    static native void set_private_key(long conn, byte[] key);

    static native byte[] get_my_peer_id(long conn);

    public static native byte[] CreatePrivateKey();

    public static native byte[] CalcPublicKey(byte[] privateKey);

    private static native String get_result_str(int res);

    private static native int init();

    native static long connection_create(OSDGConnection osdgConnection);

    native static void connection_destroy(long conn);

    native static String get_last_result_str(long conn);

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

    native static long get_version();

    public static String GetResultStr(OSDGResult res) {
        return get_result_str(res.ordinal());
    }

    public static Version GetVersion() {
        return new Version(get_version());
    }
}
