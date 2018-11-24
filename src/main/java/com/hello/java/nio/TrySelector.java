package com.hello.java.nio;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class TrySelector {

	public static void main(String[] args) throws IOException {
		System.err.println("SelectionKey.OP_READ ==== " + SelectionKey.OP_READ);
		System.err.println("SelectionKey.OP_WRITE ==== " + SelectionKey.OP_WRITE);
		System.err.println("SelectionKey.OP_CONNECT ==== " + SelectionKey.OP_CONNECT);
		System.err.println("SelectionKey.OP_ACCEPT ==== " + SelectionKey.OP_ACCEPT);
		
		Selector selector = Selector.open();
		SelectableChannel channel = DatagramChannel.open();
		channel.configureBlocking(false);
		System.out.println(" ---> " + selector.getClass().getCanonicalName() + " ---> " + channel.getClass().getCanonicalName());
		
		Object attachment = null;
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, attachment);
		
		key.selector();
		key.channel();
		key.interestOps();
		key.attachment();
		key.readyOps();
		
		key.isReadable();
		key.isWritable();
		key.isConnectable();
		key.isAcceptable();	
		
		selector.keys();
		
		int count = selector.select();
		if(count > 0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys();			
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while(keyIterator.hasNext()) {
				SelectionKey selectedKey = keyIterator.next();
				if(selectedKey.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (selectedKey.isConnectable()) {
					// a connection was established with a remote server.
				} else if (selectedKey.isReadable()) {
					// a channel is ready for reading
				} else if (selectedKey.isWritable()) {
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}
		selector.provider();
		selector.select();
		
	}

}
