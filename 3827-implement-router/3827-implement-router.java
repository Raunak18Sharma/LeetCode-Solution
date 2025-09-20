import java.util.*;

class Router {
    private final int memoryLimit;
    private final int[][] buffer; // circular buffer for FIFO
    private int head = 0, tail = 0, size = 0;

    private final HashSet<Long> seen; // duplicate check

    // Per-destination timestamp storage
    private final int MAX_DEST = 200_000 * 2 + 5; 
    private final int[][] destTimes; // dest -> timestamps array
    private final int[] destSize;    // current size per dest
    private final int[] startIndex;  // logical removal pointer

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.buffer = new int[memoryLimit][3];
        this.seen = new HashSet<>(memoryLimit * 2);

        this.destTimes = new int[MAX_DEST][];
        this.destSize = new int[MAX_DEST];
        this.startIndex = new int[MAX_DEST];
    }

    private long encode(int s, int d, int t) {
        // Encode into 64-bit: 20 bits source, 20 bits dest, 20+ bits timestamp
        return (((long) s) << 40) | (((long) d) << 20) | (long) t;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        long key = encode(source, destination, timestamp);
        if (!seen.add(key)) return false; // duplicate

        // Evict oldest if full
        if (size == memoryLimit) {
            int[] old = buffer[head];
            head = (head + 1) % memoryLimit;
            size--;
            seen.remove(encode(old[0], old[1], old[2]));
            startIndex[old[1]]++;
        }

        // Add new packet to FIFO buffer
        buffer[tail][0] = source;
        buffer[tail][1] = destination;
        buffer[tail][2] = timestamp;
        tail = (tail + 1) % memoryLimit;
        size++;

        // Add timestamp for this destination
        if (destTimes[destination] == null) {
            destTimes[destination] = new int[4]; // small initial capacity
        }
        int sz = destSize[destination];
        if (sz == destTimes[destination].length) {
            destTimes[destination] = Arrays.copyOf(destTimes[destination], sz * 2);
        }
        destTimes[destination][sz] = timestamp;
        destSize[destination] = sz + 1;

        return true;
    }

    public int[] forwardPacket() {
        if (size == 0) return new int[]{};

        int[] pkt = buffer[head];
        int s = pkt[0], d = pkt[1], t = pkt[2];
        head = (head + 1) % memoryLimit;
        size--;
        seen.remove(encode(s, d, t));
        startIndex[d]++;
        return new int[]{s, d, t};
    }

    public int getCount(int destination, int startTime, int endTime) {
        int[] arr = destTimes[destination];
        if (arr == null) return 0;

        int start = startIndex[destination];
        int sz = destSize[destination];
        if (start >= sz) return 0;

        // lower bound: first index >= startTime
        int l = start, r = sz;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= startTime) r = mid;
            else l = mid + 1;
        }
        int lo = l;

        // upper bound: first index > endTime
        l = start; r = sz;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > endTime) r = mid;
            else l = mid + 1;
        }
        int hi = l;

        return Math.max(0, hi - lo);
    }
}




/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */