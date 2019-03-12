package org.bitcoinj.examples;

import org.bitcoinj.core.FullPrunedBlockChain;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.store.FullPrunedBlockStore;
import org.bitcoinj.store.MySQLFullPrunedBlockStore;

import java.net.InetAddress;

public class MysqlDB {
    public static void main(String[] args) throws Exception {
        /*
         * This is just a test runner that will download blockchain till block
         * 390000 then exit.
         */
        FullPrunedBlockStore store = new MySQLFullPrunedBlockStore(MainNetParams.get(),
                600000, "localhost", "bitcoinj", "root", "");
          //      MainNetParams.get(), args[0], 1000, 100 * 1024 * 1024l,
          //      10 * 1024 * 1024, 100000, true, 390000);

        FullPrunedBlockChain vChain = new FullPrunedBlockChain(
                MainNetParams.get(), store);
        vChain.setRunScripts(false);

        PeerGroup vPeerGroup = new PeerGroup(MainNetParams.get(), vChain);
        vPeerGroup.setUseLocalhostPeerWhenPossible(true);
        vPeerGroup.addAddress(InetAddress.getLocalHost());

        vPeerGroup.start();
        vPeerGroup.downloadBlockChain();
    }
}
