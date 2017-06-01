package io.github.aria2;

import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by canzheng on 2017/5/30.
 */
class Aria2Test {
  @BeforeAll
  public static void initLogger() {
    System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
  }

  private Aria2 aria2;

  @BeforeEach
  public void initAria2() {
    this.aria2 = Aria2.create(
        ConfigFactory.parseResources(Aria2Test.class, "aria2.conf").resolveWith(ConfigFactory.load())
    );
    assertNotNull(aria2);
  }

  @Test
  public void testCreate() {
    Map<String, String> options = aria2.getOptions();
    assertNotNull(options);
    System.out.println(options);
  }

  @Test
  public void testConnect() throws InterruptedException, TimeoutException, ExecutionException {
    CompletionStage<Aria2Session> stage = aria2.newSession();
    assertNotNull(stage);
    stage.thenAccept(session -> {
      System.out.println("Session = " + session);
      assertNotNull(session);

      CompletionStage<Download> downloadCompletionStage = session.addUri("http://www.baidu.com/");
      assertNotNull(downloadCompletionStage);
      try {
        downloadCompletionStage.thenAccept(download -> {
          System.out.println("Download = " + download);
          assertNotNull(download);
          assertNotNull(download.getGid());
          download.onComplete().thenAccept(d -> {
            CompletionStage<DownloadStatus> statusCompletionStage = download.tellStatus();
            assertNotNull(statusCompletionStage);
            try {
              statusCompletionStage.thenAccept(downloadStatus -> {
                System.out.println("DownloadStatus = " + downloadStatus);
                assertNotNull(downloadStatus);
                assertNotNull(downloadStatus.getDownload());
                assertSame(download, downloadStatus.getDownload());
                assertNotNull(downloadStatus.getGid());
                assertEquals(download.getGid(), downloadStatus.getGid());
                assertNotNull(downloadStatus.getStatus());
                assertTrue(downloadStatus.getTotalLength() > 0);
                assertNotNull(downloadStatus.getFiles());
                assertFalse(downloadStatus.getFiles().isEmpty());
                for (FileInfo file : downloadStatus.getFiles()) {
                  assertNotNull(file);
                  System.out.println(file);
                  assertNotNull(file.getPath());
                  assertTrue(file.getLength() > 0);
                }
              });//.toCompletableFuture().get(15, TimeUnit.SECONDS);
            } catch (Exception e) {
              fail(e);
            }
          });
          download.onError().thenAccept(d -> {
            fail("Error occurs.");
          });
        });//.toCompletableFuture().get(15, TimeUnit.SECONDS);
      } catch (Exception e) {
        fail(e);
      }
    }).toCompletableFuture().get(5, TimeUnit.SECONDS);

    Thread.sleep(30000);
  }

  @Test
  public void test() {
    CompletableFuture<String> future = new CompletableFuture<>();

    future.complete("abc");

    future.thenAccept(s -> System.out.println(s));
  }
}