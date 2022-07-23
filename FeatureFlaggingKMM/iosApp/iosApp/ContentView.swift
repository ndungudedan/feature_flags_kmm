import SwiftUI
import shared




struct ContentView: View {
    @StateObject var mainViewConroller = AsyncModel { try await SeasonsState().seasons() }

	var body: some View {
        
        AsyncModelView(model: mainViewConroller) { seasons in
            Text(String(seasons.capacity))
            //List(filterSeasons(name: "Summer", seasons:seasons)) { photo in
              // Text(photo.name)
           //}
        }

        
       // TabView{
         //   WinterView().tabItem(){
           //     Image(systemName: "phone.fill")
             ///   Text("Winter")
          //  }
            //SummerView().tabItem(){
              //  Image(systemName: "phone.fill")
                ///Text("Summer")
           // }
            //AutumnView().tabItem(){
              //  Image(systemName: "phone.fill")
             //   Text("Autumn")
            //}
            //SpringView().tabItem(){
               // Image(systemName: "phone.fill")
                //Text("Spring")
            //}
        
	//}
        
}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        Group {
            ContentView()
        }
	}
}


struct AsyncResultView<Success, Content: View>: View {
    let result: AsyncResult<Success>
    let content: (_ item: Success) -> Content

    init(result: AsyncResult<Success>, @ViewBuilder content: @escaping (_ item: Success) -> Content) {
        self.result = result
        self.content = content
    }

    var body: some View {
        switch result {
            case .empty:
                EmptyView()
            case .inProgress:
                ProgressView()
            case let .success(value):
                content(value)
            case let .failure(error):
                Text(error.localizedDescription)
        }
    }
}

struct AsyncModelView<Success, Content: View>: View {
    @ObservedObject var model: AsyncModel<Success>
    let content: (_ item: Success) -> Content

    var body: some View {
        AsyncResultView(
            result: model.result,
            content: content
        )
        .task {
            await model.loadData()
        }
    }
}
