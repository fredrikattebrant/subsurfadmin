/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package se.subsurfers.members.rcp.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import se.subsurfers.members.rcp.data.ServiceSessionData;
import se.subsurfers.members.rcp.dialogs.MemberDetailsDialog;
import se.subsurfers.utils.SubsurfMember;

public class MembersMainPart {

	private Label label;
	private TableViewer tableViewer;

	@Inject
	IEclipseContext context;

	private ServiceSessionData sessionData;
	private MembersContentProvider contentProvider;
	private SubsurfMember memberData;

	public MembersMainPart() {
		System.out.println("MembersMainPart.MembersMainPart()");
	}

	@PostConstruct
	public void createComposite(Composite parent) {
		System.out.println("MembersMainPart.createComposite()");
		sessionData = ContextInjectionFactory.make(ServiceSessionData.class,
				context);
		sessionData.setUserName("<dummy>");

		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Subsurf Members");
		contentProvider = new MembersContentProvider();
		contentProvider.setModel(null, null);

		tableViewer = new TableViewer(parent);
		Table table = tableViewer.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		createColumns(table);

		tableViewer.setContentProvider(contentProvider);
		tableViewer.setLabelProvider(new MemberLabelProvider());

		sessionData.setViewer(tableViewer);
		sessionData.setContentProvider(contentProvider);
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ISelection sel = event.getSelection();
				if (sel instanceof StructuredSelection) {
					StructuredSelection ssel = (StructuredSelection) sel;
					System.out.println(ssel);
					Shell shell = event.getViewer().getControl().getShell();
					MemberDetailsDialog dialog = new MemberDetailsDialog(shell, sessionData, memberData);
					dialog.open();
				}
			}
		});
	}

	private void createColumns(Table table) {
		// Columns: Nr, First, Last, email, mobile, regdate, validdate, paiddate
		// Google:  A/1,B/2,   C/3,  L/12,  J/10,   E/5,     F/6,       I/9
		String[] titles = { "Nr", "Förnamn", "Efternamn", "E-Mail", "Mobil",
				"Registrerad", "Giltigt till", "Betalt", "Avgift" };
		int bounds[] = { 20, 100, 100, 100, 50, 80, 80, 80, 50 };

		TableViewerColumn col;
		for (int i=0; i < titles.length; i++) {
			col = createTableViewerColumn(titles[i], bounds[i], i);
		}
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			int colNum) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(false);
		return viewerColumn;
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}
}
