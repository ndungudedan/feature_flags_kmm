import SwiftUI
import shared

struct ContentView: View {
    @StateObject var featureFlagController = AsyncModel { try await MainViewState().featureFlag() }

	var body: some View {
        AsyncModelView(model: featureFlagController) { flag in
                 AsyncFeatureView(flag: flag)
        }
}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        Group {
            ContentView()
        }
	}
}

struct AsyncFeatureView: View {
    
    @StateObject var mainViewConroller = AsyncModel { try await MainViewState().seasons() }
    
    let flag: Bool
    var body: some View {
        
    AsyncModelView(model: mainViewConroller) { seasons in
        applyView(flag: flag, seasons: seasons)
        }
    }
}

struct applyView:View{
    let flag:Bool
    let seasons:[SeasonsResponse]
    var body:some View{
        
        if(flag){
            MainView(seasons: seasons)
        }
        else
        {
         TabView{
             DeprecatedMainView(photos:filterSeasons(name: "Winter", seasons:seasons)).tabItem(){
                 Image(systemName: "snow")
            Text("Winter")
            }
             DeprecatedMainView(photos:filterSeasons(name: "Summer", seasons:seasons)).tabItem(){
                Image(systemName: "sun.max.fill")
                 Text("Summer")
            }
             DeprecatedMainView(photos:filterSeasons(name: "Autumn", seasons:seasons)).tabItem(){
                Image(systemName: "wind.snow")
                 Text("Autumn")
             }
             DeprecatedMainView(photos:filterSeasons(name: "Spring", seasons:seasons)).tabItem(){
                Image(systemName: "leaf.fill")
                 Text("Spring")
             }
         
     }
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
                Text("Weather Seasons")
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



struct MainView:View{
    let seasons:[SeasonsResponse]
    var body: some View{
        ScrollView(.vertical) {
                VStack(alignment: .leading,spacing: 20) {
                    ForEach(seasons,id: \.self) { season in
                        Text(season.seasonName)
                        ScrollView(.horizontal){
                            HStack(alignment:.bottom,spacing:20){
                                ForEach(season.contents,id:\.self){photo in
                                    AsyncImage(url: URL(string: photo.url),scale: 2.0).frame(width: 200, height: 200)
                                }
                            }
                        }.frame( height:200)
                    }
                }
            }
    }
}


struct DeprecatedMainView:View{
    let photos:[SeasonPhoto]
    var body: some View{
        ScrollView(.vertical) {
                VStack(alignment: .leading,spacing: 20) {
                    ForEach(photos,id: \.self) { photo in
                                    AsyncImage(url: URL(string: photo.url)).frame(width: 250, height: 300)
                                
                    }
                }
            }
    }
}



