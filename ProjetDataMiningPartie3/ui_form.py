# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'untitled.ui'
#
# Created by: PyQt5 UI code generator 5.15.4
#
# WARNING: Any manual changes made to this file will be lost when pyuic5 is
# run again.  Do not edit this file unless you know what you are doing.


import sys
from PyQt5.uic import loadUi
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QApplication, QWidget, QStackedWidget, QFileDialog
from PyQt5.QtGui import QPixmap, QImage

from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(1066, 559)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.widget = QtWidgets.QFrame(self.centralwidget)
        self.widget.setGeometry(QtCore.QRect(0, 0, 261, 561))
        self.widget.setMinimumSize(QtCore.QSize(120, 0))
        self.widget.setStyleSheet("\n"
"weight : 400px;\n"
"height: 300px;\n"
"background-color : rgb(120,120,120) \n"
"")
        self.widget.setObjectName("widget")
        self.verticalWidget = QtWidgets.QWidget(self.widget)
        self.verticalWidget.setGeometry(QtCore.QRect(10, 20, 241, 291))
        self.verticalWidget.setObjectName("verticalWidget")
        self.verticalLayout = QtWidgets.QVBoxLayout(self.verticalWidget)
        self.verticalLayout.setContentsMargins(0, 0, 0, 0)
        self.verticalLayout.setObjectName("verticalLayout")
        self.pushButton = QtWidgets.QPushButton(self.verticalWidget)
        self.pushButton.setObjectName("pushButton")
        self.verticalLayout.addWidget(self.pushButton)
        self.pushButton_4 = QtWidgets.QPushButton(self.verticalWidget)
        self.pushButton_4.setObjectName("pushButton_4")
        self.verticalLayout.addWidget(self.pushButton_4)
        self.pushButton_3 = QtWidgets.QPushButton(self.verticalWidget)
        self.pushButton_3.setObjectName("pushButton_3")
        self.verticalLayout.addWidget(self.pushButton_3)
        self.pushButton_2 = QtWidgets.QPushButton(self.verticalWidget)
        self.pushButton_2.setObjectName("pushButton_2")
        self.verticalLayout.addWidget(self.pushButton_2)
        self.widget_2 = QtWidgets.QWidget(self.centralwidget)
        self.widget_2.setGeometry(QtCore.QRect(490, 20, 801, 561))
        self.widget_2.setStyleSheet("background-color : rgb(180,180,180);\n"
"")
        self.widget_2.setObjectName("widget_2")
        self.scrollArea = QtWidgets.QScrollArea(self.widget_2)
        self.scrollArea.setGeometry(QtCore.QRect(80, 90, 661, 271))
        self.scrollArea.setWidgetResizable(True)
        self.scrollArea.setObjectName("scrollArea")
        self.scrollAreaWidgetContents = QtWidgets.QWidget()
        self.scrollAreaWidgetContents.setGeometry(QtCore.QRect(0, 0, 659, 269))
        self.scrollAreaWidgetContents.setObjectName("scrollAreaWidgetContents")
        self.tableView = QtWidgets.QTableView(self.scrollAreaWidgetContents)
        self.tableView.setGeometry(QtCore.QRect(100, 60, 256, 192))
        self.tableView.setObjectName("tableView")
        self.scrollArea.setWidget(self.scrollAreaWidgetContents)
        self.horizontalLayoutWidget = QtWidgets.QWidget(self.centralwidget)
        self.horizontalLayoutWidget.setGeometry(QtCore.QRect(300, 140, 160, 80))
        self.horizontalLayoutWidget.setObjectName("horizontalLayoutWidget")
        self.horizontalLayout = QtWidgets.QHBoxLayout(self.horizontalLayoutWidget)
        self.horizontalLayout.setContentsMargins(0, 0, 0, 0)
        self.horizontalLayout.setObjectName("horizontalLayout")
        MainWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.widget.setToolTip(_translate("MainWindow", "<html><head/><body><p>cvdb</p></body></html>"))
        self.pushButton.setText(_translate("MainWindow", "PushButton"))
        self.pushButton_4.setText(_translate("MainWindow", "PushButton"))
        self.pushButton_3.setText(_translate("MainWindow", "PushButton"))
        self.pushButton_2.setText(_translate("MainWindow", "PushButton"))


app = QApplication(sys.argv)
Welcome = Ui_MainWindow()
widget = QStackedWidget(Welcome)
widget.setFixedHeight(600)
widget.setFixedWidth(1200)
widget.show()
try:
    sys.exit(app.exec_())
except:
    print("Exiting error!")