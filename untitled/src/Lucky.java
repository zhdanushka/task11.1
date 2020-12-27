public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        private int id;

        public LuckyThread(int id) {
            this.id = id;
        }

        public void run() {
            for (int i = 0; i < 999999; i++) {
                if (i % 3 == id) {
                    if ((i % 10) + (i / 10) % 10 + (i / 100) % 10 == (i / 1000) % 10 + (i / 10000) % 10 + (i / 100000) % 10) {
                        System.out.println(i);
                        synchronized (this) {
                            count++;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread(0);
        Thread t2 = new LuckyThread(1);
        Thread t3 = new LuckyThread(2);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
