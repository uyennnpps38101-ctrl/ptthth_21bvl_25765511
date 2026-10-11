package lab4.exercise;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostAndUriInspector {
    public static void main(String[] args) {
        // Kiểm tra thiếu tham số (yêu cầu nhận cả hostname và URI qua args)[cite: 22]
        if (args.length < 2) {
            System.err.println("Cú pháp sử dụng: java lab4.exercise.HostAndUriInspector <hostname> <URI>");
            return;
        }

        String hostname = args[0];
        String uriStr = args[1];

        // 1. Phân tích Hostname
        System.out.println("=== THÔNG TIN HOSTNAME: " + hostname + " ===");
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                String ipType = "Không xác định";
                if (address instanceof Inet4Address) {
                    ipType = "IPv4";
                } else if (address instanceof Inet6Address) {
                    ipType = "IPv6";
                }
                
                System.out.println("- IP: " + address.getHostAddress() + " (" + ipType + ")");
                System.out.println("  Canonical: " + address.getCanonicalHostName());
                System.out.println("  Loopback: " + address.isLoopbackAddress());
                System.out.println("  Site local: " + address.isSiteLocalAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Lỗi: Không phân giải được hostname: " + hostname);
        }

        System.out.println();

        // 2. Phân tích URI bằng java.net.URI[cite: 22]
        System.out.println("=== THÔNG TIN URI: " + uriStr + " ===");
        try {
            URI uri = new URI(uriStr);
            System.out.println("- Scheme: " + uri.getScheme());
            System.out.println("- Host: " + uri.getHost());
            System.out.println("- Port: " + (uri.getPort() == -1 ? "Mặc định" : uri.getPort()));
            System.out.println("- Path: " + uri.getPath());
            System.out.println("- Query: " + uri.getQuery());
            System.out.println("- Fragment: " + uri.getFragment());
        } catch (URISyntaxException e) {
            System.err.println("Lỗi: URI không hợp lệ - " + e.getMessage());
        }
    }
}