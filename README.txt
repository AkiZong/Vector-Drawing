Implemented a vector-drawing program by using MVC, consisting of a canvas and tool palettes. Your program will support drawing shapes, dragging them to reposition on-screen, and changing properties like color and line width.


**************************************  Description of the Vector Drawing:  **************************************

makefile:
	- “make run” will run the application

————————————————————

Fit-To-Windows does not work, so the view is full size by default.

When the canvas is larger than 800*800, the “Tools”, “Color” buttons in the left side will be larger to fit for the whole view.


Tools: 
	- In terms of interface, a selected tool will be a recessed button.
	- Tools 

	- “selection”
		-> disabled when there is nothing on canvas (a message window to disallow you to use it).
		-> the border of selective shape will be dashed.
		-> the “thickness” and “color” will reflect current selected shape.
		-> if a shape is selected, you can move it around the screen.
		-> if you click the position outside of a selected shape, the shape will be unselected.
		-> press “ESC” will cancel selection.
		-> if there are overlapping shapes, it will select the latest drawn shape.

	- “erase”
		-> disabled when there is nothing on canvas (a message window to disallow you to use it).
		-> selected shape will be deleted.
		-> if there are overlapping shapes, it will delete the latest drawn shape.

	- “fill”
		-> disabled when there is nothing on canvas (a message window to disallow you to use it).
		-> selected shape will be filled with chosen color.
		-> if there are overlapping shapes, it will fill the latest drawn shape.

	- “line”, “circle”, “rectangle”
		-> before drawing, choose one of them, the color will be black by default.



Color:
	- In terms of interface, a selected color will be a recessed button.
	- you can choose color from color box (6 colours).
	- “Chooser” provides more colors.
	- right-click a color button and choose a new color for that button from a color-chooser dialog



Thickness:
	- In terms of interface, a selected thickness will be RED, others will be BLACK.
	- There are 4 level of thickness can be chosen.



File-New:
	- when there are shapes on canvas, you will get a confirmation window to ensure you want to save it or not first.
	- otherwise, you can get a new file



File-Save:
	- you can save the file at anywhere you like, and the saved file will be a Text file
	- if there is no shape on canvas, you cannot save a blank file (message hint).



File-Load:
	- you can load a Text file (the file you previously saves)
	- if there exist shape(s) on the canvas, you will get a conformation window to ensure you want to save it or not first.
		And then, the application will load the file for you.



*********************************************  Enhancements:  *********************************************

1. Change the scale/size:
	- When you choose a shape, a small black square will appear at the corner of the selected shape,
		you can click on that square to scale/resize the shape.

2. Customizable color palette:
	- right-click a button and choose a new color for that button from a color-chooser dialog


***************************************  Development Environment:  ***************************************

Mac OS
Version 10.11.3
java 1.7.0_81 

