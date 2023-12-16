## Task Queue Manager

The **Task Queue Manager** module is designed to enhance your experience with Mendix's Task Queue. It provides information on the queued and processed tasks, allows you to retry failed tasks and schedule new microflows/tasks with a specific execution time.

## Features

- **Comprehensive View**: Easily view all queued and processed tasks in a user-friendly interface.
- **Manual Retry**: Manually retry failed tasks
- **Microflow Scheduling**: Schedule microflows or tasks to execute at specific times, giving you greater control over task execution.

## Usage

1. Import the module into your project.
2. Setup the Administrator userrole for the module
3. Add the `SNIP_TaskQueueManager` snippet to a backend page in your application. This snippet is your control center for viewing queued & processed tasks and retrying failed ones as needed.

## Schedule Microflows/Tasks

This module also provided a way to schedule a microflow to execute at a specific time. This is possible because the Task Queue feature has a StartAt datetime which the scheduled events in Mendix uses. So I have added Java Actions that can accomplish this in the USE_ME folder. 

### Configuring the Java Action:
1. Select the desired Microflow
2. Enter the Queue Name to trigger the microflow, including the module name (e.g., `MyFirstModule.TQ_Default`).
3. Set `ExecuteAt` with the DateTime value for when you want the microflow to start.
4. Configure up to 2 objects or 2 strings as parameters. Ensure correct naming and values for successful execution. They can also be set to empty.

## Issues, Suggestions, and Feature Requests

If you encounter any issues, have suggestions for improvements, or want to request new features, please visit our [GitHub repository](https://github.com/hunter-koppen/TaskQueueManager/issues).
