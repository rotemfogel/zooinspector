/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.zookeeper.inspector;

import org.apache.zookeeper.inspector.gui.ZooInspectorPanel;
import org.apache.zookeeper.inspector.logger.LoggerFactory;
import org.apache.zookeeper.inspector.manager.ZooInspectorManagerImpl;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class ZooInspector {
	/**
	 * @param args - not used. The value of these parameters will have no effect
	 *             on the application
	 */
	public static void main(String[] args) {
		try {
//          Dimension screenSize = getScreenResolution();
//          int screenWidth = screenSize.width;
//          int screenHeight = screenSize.height;
			// System.out.println("screenWidth: " + screenWidth + ", screenHeight: " + screenHeight);
			
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame frame = new JFrame("ZooInspector");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// debug
//            frame.setSize(screenWidth * 2 / 3, screenHeight);
//            frame.setVisible(true);
			
			final ZooInspectorPanel zooInspectorPanel = new ZooInspectorPanel(
					new ZooInspectorManagerImpl());
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					super.windowClosed(e);
					zooInspectorPanel.disconnect(true);
				}
			});
			
			frame.setContentPane(zooInspectorPanel);
			frame.setSize(1024, 768);
//            frame.setSize(screenWidth * 2 / 3, screenHeight);
			frame.setVisible(true);
		} catch (Exception e) {
			LoggerFactory.getLogger().error(
					"Error occurred loading ZooInspector", e);
			JOptionPane.showMessageDialog(null,
					"ZooInspector failed to start: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}