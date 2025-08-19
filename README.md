# The batch that can handle multiple jobs with single jar

What I Love About This Pattern:
### Single Entry Point
- One JAR file handles multiple batch jobs 
- You just pass a parameter to choose which batch to run (dynamic job selection)
- Super clean deployment - no need for multiple apps

### Clean Architecture
- RunnableJob interface = consistent contract
- MainBatchLauncher = factory/dispatcher
- Each batch = focused, single responsibility
- Dependency injection handles the wiring

### Easy to Scale
- Want Batch 3? Just create another component
- Spring auto-discovers it via @Component("batch3")
- No changes to MainBatchLauncher needed

### The Flow:
- App starts with command line arg (e.g., "batch1")
- MainBatchLauncher gets Map<String, RunnableJob> injected
- Looks up the right batch from the map
- Executes it via the common interface
- Clean exit

## Crontab Setup
`crontab -e`

```
#!/bin/bash
*/5 * * * * /usr/bin/java -jar /Users/tyna/Document/nexgen/workspace/demo.batch/target/demo.batch-0.0.1-SNAPSHOT.jar DateTimeBatch
```

`crontab -l`
