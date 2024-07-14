## Store Images into File

![](../images/./store-into-filesystem.png)

In this approach, we save the image into a folder in file system, then we store the path of that image into a database.

### We need to use this option in the following situations :

- #### If we have large amount of image uploads and downloads, storing them in the file system can reduce the load on your database and improve the overall performance.
- #### If we want faster access especially for large files
