package spark.embeddedserver;

/**
 * Supports building using Virtual Threads
 */
public interface VirtualThreadAware {

    /**
     * Set the usage of the virtual thread
     * @param useVThread if true, use v-thread, else system thread
     */
    default void useVThread(boolean useVThread){}

    /**
     * Whether the system is using v-thread or not
     * @return true if it is using, false otherwise
     */
    default boolean useVThread(){ return false; }

    class Base implements VirtualThreadAware {
        protected boolean useVThread = false;

        public void useVThread(boolean useVThread){
            this.useVThread = useVThread;
        }
        public boolean useVThread(){
            return useVThread;
        }
    }

    class Proxy implements VirtualThreadAware {
        protected VirtualThreadAware underlying;
        public Proxy( VirtualThreadAware underlying){
            this.underlying = underlying;
        }

        public void useVThread(boolean useVThread){
            underlying.useVThread(useVThread);
        }
        public boolean useVThread(){
            return underlying.useVThread();
        }
    }
}
