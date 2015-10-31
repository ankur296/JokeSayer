package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.jokes.Joker;

public class AsyncTaskAndroidTest extends AndroidTestCase implements TestTask{
    private SyncronizeTalker async = null;

    public void testAsyncTaskReturns() {
        Log.e("Test", "testAsyncTaskReturns enter");
        new EndpointsAsyncTask(this).execute();
        async = new SyncronizeTalker();
        async.doWait(); // <--- wait till "async.doNotify()" is called

    }

    @Override
    public void onDone(String jokeFromServer) {
        Log.e("Test", "onDone : "+jokeFromServer);

        assertEquals(new Joker().getJoke()/*"This is totally a funny joke"*/, jokeFromServer);
        async.doNotify(); // release "async.doWait()" (on this step the unitest is finished)
    }

    public class SyncronizeTalker {
        public void doWait(long l){
            synchronized(this){
                try {
                    this.wait(l);
                } catch(InterruptedException e) {
                }
            }
        }



        public void doNotify() {
            synchronized(this) {
                this.notify();
            }
        }


        public void doWait() {
            synchronized(this){
                try {
                    this.wait();
                } catch(InterruptedException e) {
                }
            }
        }
    }
}