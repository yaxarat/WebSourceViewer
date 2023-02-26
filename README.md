# WebSourceViewer
A simple app that shows website and its HTML source side by side:

<p align="center">
  <img width="800" alt="Screenshot 2023-02-25 at 5 28 05 PM" src="https://user-images.githubusercontent.com/27980758/221382462-2dc4e28e-c3ef-48cb-a01a-624286b6139f.png">
</p>

## Purpose
This is a simple app created to showcase the usage of shared `viewModel`. In this sample app, the two fragments (enter url screen & website-html screen) share a `viewModel` (`commonViewModel`) between them. This allows for state/data sharing without having to pass bundles during fragment transactions.

A simple diagram of this architecture would look like this:

```mermaid
graph TD;
    Activity-->SharedViewModel;
    SharedViewModel-->Fragment1;
    SharedViewModel-->Fragment2;
```

The shared viewModel here is scoped to the lifecycle of the activity and can be accessed in child fragments via: 

```kotlin
private val sharedViewModel: SharedViewModel by activityViewModels()
```


Enter url             |  See the source
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/27980758/221382547-eec38746-51f5-4c49-ad68-d9d24bd151ad.png)  |  ![](https://user-images.githubusercontent.com/27980758/221382474-b4a3facf-68a3-42f5-94f7-1d9214c92ce6.png)
