package net.dominionserver.dominiontweaks.Utils;

import net.dominionserver.dominiontweaks.DominionTweaks;
import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public class NetherTunnelUtils {
    private static int[] distance2(char n, int x1, int z1, int lx1, int lz1, int lx2, int lz2) {

        double dx = x1 - lx1;
        double dz = z1 - lz1;

        double segment_dx = lx2 - lx1;
        double segment_dz = lz2 - lz1;

        double dotProduct = dx * segment_dx + dz * segment_dz;

        double segmentLengthSquared = segment_dx * segment_dx + segment_dz * segment_dz;

        double t;

        if (segmentLengthSquared == 0.0) {
            t = 0.0;
        } else {
            t = Math.max(0, Math.min(1, dotProduct / segmentLengthSquared));
        }

        double closestX = lx1 + t * segment_dx;
        double closestZ = lz1 + t * segment_dz;

        double distance = Utils.distSquared(x1, z1, (int) closestX, (int) closestZ);

        return new int[] {n, (int) Math.round(distance), (int) Math.round(closestX), (int) Math.round(closestZ)};
    }

    //Check if coordinates are inside protected area
    private static int isProtected(int radius, int x, int z, int nx, int nz){
        int dist = Utils.distSquared(x,z,nx,nz);
        return dist <= radius ? -1 : dist;
    }
    /**
     * @param tun Tunnel's name
     * @param x1 Player's nether point
     * @param z1 Player's nether point
     * @param lx1 Tunnel nether point
     * @param lz1 Tunnel nether point
     * @return A string such as "1.18 x/z(N|S|E|W) is the closest, coordinates X: Z:
     */
    private static String distance(String tun, int x1, int z1, int lx1, int lz1) {
        //N S E W
        int[][] distances = {
                distance2('N', x1, z1, lx1, lz1, lx1, lz1 - 1250),
                distance2('S', x1, z1, lx1, lz1, lx1, lz1 + 1250),
                distance2('E', x1, z1, lx1, lz1, lx1 + 1250, lz1),
                distance2('W', x1, z1, lx1, lz1, lx1 - 1250, lz1)
        };

        int[] minDistance = distances[0];
        for (int[] distance : distances) {
            if (distance[1] < minDistance[1]) {
                minDistance = distance;
            }
        }
        return String.format("%s %d%s is the closest, coordinates: X: %d Z: %d", tun, minDistance[0] == 'N' || minDistance[0] == 'S' ? z1 : x1, (char) minDistance[0], minDistance[2], minDistance[3]);
    }

    public static String getClosestTunnel(Location L){
        return getClosestTunnel(L.getBlockX(), L.getBlockZ());
    }
    public static String getClosestTunnel(int[] i){
        return getClosestTunnel(i[0], i[1]);
    }
    public static String getClosestTunnel(int x, int z){
        //Load tunnels from config Map<String, int/string>
        List<Map<?,?>> t = DominionTweaks.instance.getConfig().getMapList("Tunnels.Coordinates");
        int radius = DominionTweaks.instance.getConfig().getInt("Tunnels.Radius", 25);
        String tunnel = "";
        int tunX = Integer.MIN_VALUE, tunZ = Integer.MIN_VALUE, dist = Integer.MAX_VALUE;
        for (Map<?, ?> m : t){
            String name = (String) m.get("name");
            int tmpX = (int) m.get("x");
            int tmpZ = (int) m.get("z");
            int _dist = isProtected(radius, x, z, tmpX, tmpZ);
            if (_dist < 0)
                return "These coordinates are inside a protected range.";
            if (_dist < dist){
                tunX = tmpX;
                tunZ = tmpZ;
                dist = _dist;
                tunnel = name;
            }
        }
        return distance(tunnel, x, z, tunX, tunZ);
    }
}
