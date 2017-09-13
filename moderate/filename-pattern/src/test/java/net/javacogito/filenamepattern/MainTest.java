package net.javacogito.filenamepattern;

import java.io.IOException;
import java.net.URL;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void buildRegExpPatternTest(){
    String pattern01 = "*7*";
    String pattern02 = "*.???";
    String pattern03 = "*[0123456789]*[auoei]*";
    String pattern04 = "*pdf";

    System.out.println(Main.buildRegExpPattern(pattern04));
    Assert.assertEquals(".*7.*\\b", Main.buildRegExpPattern(pattern01));
    Assert.assertEquals(".*\\....\\b", Main.buildRegExpPattern(pattern02));
    Assert.assertEquals(".*[0123456789].*[auoei].*\\b", Main.buildRegExpPattern(pattern03));
    Assert.assertEquals(".*pdf\\b", Main.buildRegExpPattern(pattern04));
  }

  @Test
  public void filterTest(){
    String[] files01 = {"johab.py", "gen_probe.ko", "palmtx.h", "macpath.py", "tzp", "dm-dirty-log.h", "bh1770.h", "pktloc",  "faillog.8.gz", "zconf.gperf"};
    String pattern01 = "*7*";
    String[] expected01 = {"bh1770.h"};

    String[] files02 = {"max_user_watches", "arptables.h", "net_namespace Kannada.pl", "menu_no_no.utf-8.vim", "shtags.1", "unistd_32_ia32.h", "gettext-tools.mo", "ntpdate.md5sums", "linkat.2.gz"};
    String pattern02 = "*.???";
    String[] expected02 = {"menu_no_no.utf-8.vim"};

    String[] files03 = {"IBM1008_420.so", "zfgrep", "limits.conf.5.gz", "tc.h", "ilogb.3.gz", "limits.conf", "CyrAsia-TerminusBold28x14.psf.gz", "nf_conntrack_sip.ko",
      "DistUpgradeViewNonInteractive.pyc NFKDQC"};
    String pattern03 = "*[0123456789]*[auoei]*";
    String[] expected03 = {"IBM1008_420.so"};


    String[] files04 = {"OldItali.pl", "term.log", "plymouth-upstart-bridge", "rand.so", "libipw.ko", "jisfreq.pyc", "impedance-analyzer", "xmon.h", "1.5.0.3.txt", "bank"};
    String pattern04 = "*.pdf";
    String[] expected04 = {};


    Assert.assertArrayEquals(expected01, Main.filter(pattern01, files01).toArray());
    Assert.assertArrayEquals(expected02, Main.filter(pattern02, files02).toArray());
    Assert.assertArrayEquals(expected03, Main.filter(pattern03, files03).toArray());
    Assert.assertArrayEquals(expected04, Main.filter(pattern04, files03).toArray());
    System.out.println(Main.filter(pattern04, files04));
  }
}