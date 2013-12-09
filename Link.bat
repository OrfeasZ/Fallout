@echo off
C:
rmdir "C:\xampp\htdocs"
mklink /D "C:\xampp\htdocs" "%~dp0\htdocs"