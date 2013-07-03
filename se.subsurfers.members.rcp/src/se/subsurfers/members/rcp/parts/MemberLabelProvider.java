package se.subsurfers.members.rcp.parts;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.gdata.data.spreadsheet.ListEntry;

public class MemberLabelProvider extends ColumnLabelProvider implements
		ITableLabelProvider {

	// Google:  A/1,B/2,C/3,L/12, J/10,E/5,F/6,I/9,H/8
	int[] columnMapping = { 0, 1, 2, 11, 9, 4, 5, 9, 8 };

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ListEntry row = (ListEntry) element;
		int ix = 0;
		String text = "-";
		for (String tag : row.getCustomElements().getTags()) {
			if (ix == columnMapping[columnIndex]) {
				text = row.getCustomElements().getValue(tag);
				break;
			}
			ix++;
		}
		//System.out.println("Text: " + text + " - column=" + columnIndex);
		return text;
	}

}
