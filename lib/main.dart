import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(OpenContacts());

class OpenContacts extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'OpenContacts',
      theme: ThemeData(
        primarySwatch: Colors.yellow,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const platform =
      const MethodChannel('flutter_contacts/launch_contacts');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text("Open Contacts"),
          color: Colors.yellow,
          onPressed: () async => launchContacts(),
        ),
      ),
    );
  }

  void launchContacts() async {
    try {
      await platform.invokeMethod('launch');
    } on PlatformException catch (e) {
      print("Failed to launch contacts: ${e.message}");
    }
    setState(() {
    });
  }
}
